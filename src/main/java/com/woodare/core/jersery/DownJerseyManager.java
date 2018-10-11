package com.woodare.core.jersery;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.jersey.IWebService;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.JsonUtils;

/**
 * 
 * @author lu_feng
 * 
 */
@Service
public class DownJerseyManager {
	// private Log log = LogFactory.getLog(DownJerseyManager.class);

	public DownJerseyManager() {
	}

	/**
	 * 
	 * @param module
	 * @param action
	 * @param requestString
	 * @return
	 * @throws AbstractWooException
	 */
	public Object execute(HttpServletRequest httpRequest, String module, String action, String decodePayload) throws AbstractWooException {
		IWebService webservice = getWebService(module);

		Method actionMethod = webservice.getActionMethod(action);
		Object request = getRequestData(actionMethod, decodePayload);

		Transactional transactional = actionMethod.getAnnotation(Transactional.class);

		if (transactional != null && transactional.propagation() == Propagation.REQUIRED) {
			return webservice.execute(httpRequest, actionMethod, request);
		} else {
			return webservice.executeWithNoTransaction(httpRequest, actionMethod, request);
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	private IWebService getWebService(String path) {
		// log.debug(String.format("Loading bean instance from spring context
		// with %sWebService", path));
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
