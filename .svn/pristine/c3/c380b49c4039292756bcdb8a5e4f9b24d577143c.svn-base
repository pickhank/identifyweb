/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.core.security;

import com.woodare.framework.utils.JavaMD5Hash;

/**
 * 
 * @author lu_feng
 * 
 */
public class CustomPasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return JavaMD5Hash.md5(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().substring(0, 32).equals(encode(encodedPassword + rawPassword.toString().substring(32)));
	}

}
