package com.woodare.framework.jersey;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.data.impl.WebRequestData;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.jersey.authenticate.AuthenticateHelper;

/**
 * 
 * @author lu_feng
 * 
 */
public abstract class AbstractWebService implements IWebService {
    private Log log = LogFactory.getLog(AbstractWebService.class);

    private Map<String, Boolean> authorities = new HashMap<String, Boolean>();

    @Autowired
    protected HttpServletRequest request;

    /**
     * 
     * @param webservice
     * @param action
     * @return
     * @throws ServiceHandlerException
     */
    public Method getActionMethod(String action) throws AbstractWooException {
        Method rel = null;
        try {
            Method[] methods = this.getClass().getMethods();
            for (Method method : methods) {
                if (action.equals(method.getName()) && method.getParameterTypes() != null) {
                	if(method.getParameterTypes().length == 2) {
                		rel = method;
                        break;	
                	}
                	else if(method.getParameterTypes().length == 1) {
                        rel = method;
                        break;	
                	}
                }
            }
            if (rel == null) {
                throw new Exception(String.format("Could not find method %s with one parameter", action));
            }
        } catch (NoSuchMethodException e) {
            log.error(e, e);
            throw new AbstractWooException(String.format("Could not find action: %s", action));
        } catch (SecurityException e) {
            log.error(e, e);
            throw new AbstractWooException(String.format("Could not find action: %s", action));
        } catch (Exception e) {
            log.error(String.format("Invoke handler action error:"));
            throw new AbstractWooException(e);
        }
        return rel;
    }

    /**
     * Add action to exclude to do authority
     * 
     * @param action
     */
    public void addExcludeAuthority(String action) {
        authorities.put(action, false);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object execute(HttpServletRequest httpRequest, Method actionMethod, Object request) throws AbstractWooException {
        return executeWithReflection(httpRequest, actionMethod, request);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Object executeWithNoTransaction(HttpServletRequest httpRequest, Method actionMethod, Object request) throws AbstractWooException {
        return executeWithReflection(httpRequest, actionMethod, request);
    }

    @Override
    public Object executeWithReflection(HttpServletRequest httpRequest, Method actionMethod, Object requestData) throws AbstractWooException {
        try {
            actionMethod.setAccessible(true);
            if(actionMethod.getParameterTypes().length == 1) {
            	return actionMethod.invoke(this, requestData);
            }
            else {
            	return actionMethod.invoke(this, requestData, httpRequest);	
            }
            
        } catch (Exception e) {
            if (e instanceof java.lang.reflect.InvocationTargetException) {
                Throwable innerException = ((java.lang.reflect.InvocationTargetException) e).getTargetException();
                if (innerException instanceof AbstractWooException) {
                    throw (AbstractWooException) innerException;
                }
            }
            log.error(String.format("Invoke handler action error:"));
            throw new AbstractWooException(getInnerThrowableException(e));
        }
    }
    
    private Throwable getInnerThrowableException(Throwable e) {
        if(e instanceof java.lang.reflect.InvocationTargetException) {
            return getInnerThrowableException(((java.lang.reflect.InvocationTargetException) e).getTargetException());
        }
        return e;
    } 

    @Override
    public boolean checkAuthority(String action, WebRequestData requestData) {

        if (this instanceof INoneAuthService) {
            return true;
        }
        if (authorities.containsKey(action) && !authorities.get(action)) {
            return true;
        }
        boolean ret = AuthenticateHelper.hasValidToken(requestData.getToken());
        if (!ret) {
            try {
                ret = doJpaTokenAutheticate(requestData.getToken());
            } catch (AbstractWooException e) {
                ret = false;
            }
        }
        return ret;
    }

    public abstract boolean doJpaTokenAutheticate(String token) throws AbstractWooException;
}
