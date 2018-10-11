package com.woodare.core.base;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.jersey.AbstractWebService;
import com.woodare.framework.jersey.authenticate.AuthenticateHelper;
import com.woodare.framework.utils.StringUtils;

/**
 * @author lu_feng
 */
public abstract class AbstractBusiWebService extends AbstractWebService {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AbstractBusiWebService.class);

	@Autowired
	protected ISystemUserDAO systemUserDAO;

	@Override
	public boolean doJpaTokenAutheticate(String authToken) throws AbstractWooException {
		return false;
	}

	/**
	 * @param val
	 * @param fieldName
	 * @param maxlength
	 * @param required
	 * @throws AbstractWooException
	 */
	protected void verifyField(String val, String fieldName, int maxlength, boolean required) throws AbstractWooException {
		if (required && StringUtils.isEmpty(val)) {
			throw new MessageWooException(fieldName + " is required");
		}
		else if (val != null && val.length() > maxlength) {
			throw new MessageWooException(fieldName + "'s max length is 50");
		}
	}

	/**
	 * Return verified player's ID attached to current request.
	 * 
	 * @return
	 */
	protected String getUserId() {
		return AuthenticateHelper.getRequestTokenSession(request).getUserId();
	}

	/**
	 * Return whether user is logined
	 * 
	 * @return
	 */
	protected boolean isLogined() {
		return AuthenticateHelper.getRequestTokenSession(request) != null;
	}

	/**
	 * Return verified player's ID attached to current request.
	 * 
	 * @return
	 */
	protected String getAuthToken() {
		return AuthenticateHelper.getRequestTokenSession(request).getAuthToken();
	}
}
