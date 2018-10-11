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
package com.woodare.core.web.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: MyTag
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Apr 4, 2016
 * 
 */
public class MyOutTag extends TagSupport {
	private static final long serialVersionUID = -7155683667234179057L;
	private String value;

	@Override
	public int doEndTag() throws JspException {
		JspWriter writer = this.pageContext.getOut();
		StringBuffer content = new StringBuffer();
		if (StringUtils.isEmpty(value)) {
			content.append(" - ");
		} else {
			try {
				if (Integer.valueOf(value) == 0) {
					content.append(" - ");
				} else {
					content.append(value);
				}
			} catch (Exception e) {
				content.append(value);
			}
		}
		try {
			writer.print(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
