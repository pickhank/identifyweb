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

import java.math.BigDecimal;

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
 */
public class MyPercentTag extends TagSupport {
	private static final long serialVersionUID = -7155683667234179057L;
	private String value;
	private String total;

	@Override
	public int doEndTag() throws JspException {
		JspWriter writer = this.pageContext.getOut();
		StringBuffer content = new StringBuffer();
		try {
			BigDecimal v = null;
			if (StringUtils.isNotEmpty(value)) {
				v = new BigDecimal(value);
			}

			BigDecimal t = null;
			if (StringUtils.isNotEmpty(total)) {
				t = new BigDecimal(total);
			}
			if (v == null || t == null || t.compareTo(new BigDecimal(0)) <= 0) {
				content.append(" - ");
			} else {
				BigDecimal percent = v.multiply(new BigDecimal(100)).divide(t, 2, BigDecimal.ROUND_HALF_UP);
				content.append(percent.toString() + "%");
			}
			writer.print(content.toString());
		} catch (Exception e) {
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
