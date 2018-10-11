package com.woodare.framework.jersey;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.data.impl.WebRequestData;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.exception.UnauthorityWooException;
import com.woodare.framework.jersey.authenticate.AuthenticateHelper;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.JsonUtils;

/**
 * 
 * @author lu_feng
 * 
 */
@Service
public class JerseyManager {
    private Log log = LogFactory.getLog(JerseyManager.class);

    public JerseyManager() {
    }

    /**
     * 
     * @param module
     * @param action
     * @param requestString
     * @return
     * @throws AbstractWooException
     */
    public Object execute(HttpServletRequest httpRequest, String module, String action, String payload) throws AbstractWooException {
        IWebService webservice = getWebService(module);

        Method actionMethod = webservice.getActionMethod(action);
        WebRequestData requestData = JsonUtils.fromJson(payload, WebRequestData.class);
        if (!webservice.checkAuthority(action, requestData)) {
            throw new UnauthorityWooException("No authority");
        }

        AuthenticateHelper.bindToRequest(requestData.getToken(), httpRequest);

        Object request = getRequestData(actionMethod, requestData.getPayload());

        Transactional transactional = actionMethod.getAnnotation(Transactional.class);

        if (transactional != null && transactional.propagation() == Propagation.REQUIRED) {
            return webservice.execute(httpRequest, actionMethod, request);
        } else {
            return webservice.executeWithNoTransaction(httpRequest, actionMethod, request);
        }
    }
    

    /**
     * 
     * @param module
     * @param action
     * @param requestString
     * @return
     * @throws AbstractWooException
     */
    public Object doExecute(HttpServletRequest httpRequest, String module, String action, Object methodParam) throws AbstractWooException {
        IWebService webservice = getWebService(module);

        Method actionMethod = webservice.getActionMethod(action);
        Transactional transactional = actionMethod.getAnnotation(Transactional.class);

        if (transactional != null && transactional.propagation() == Propagation.REQUIRED) {
            return webservice.execute(httpRequest, actionMethod, methodParam);
        } else {
            return webservice.executeWithNoTransaction(httpRequest, actionMethod, methodParam);
        }
    }

    /**
     * 
     * @param path
     * @return
     */
    private IWebService getWebService(String path) {
        log.debug(String.format("Loading bean instance from spring context with %sWebService", path));
        String beanName = path + "WebService";
        return (IWebService) ApplicationContextHolder.getApplicationContext().getBean(beanName);
    }

    /**
     * 
     * @param method
     * @param requestString
     * @return
     */
    private Object getRequestData(Method method, String payload) {
        Type paramsClass = method.getGenericParameterTypes()[0];
        Object request = (Object) JsonUtils.fromJson(payload, paramsClass);
        return request;
    }
}
