package com.woodare.framework.web.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * 
 * ClassName: FullPathParseTag
 * 
 * @description
 * @author lu_feng
 * @Date 2013-3-8
 * 
 */
public class CustomUrlTag extends org.apache.taglibs.standard.tag.rt.core.UrlTag {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        
		pageContext.setAttribute("infloat", (String)request.getParameter("infloat"));
		pageContext.setAttribute("handlekey", (String)request.getParameter("handlekey"));
		
        String relativeUrl = this.value;
        
        if(relativeUrl.indexOf("?") == -1) {
        	relativeUrl += "?";
        }
        if(request.getParameter("infloat") != null) {
			relativeUrl += "infloat=" + request.getParameter("infloat")
					+ "&handlekey=" + request.getParameter("handlekey")
					+ "&noiframe=true";
        }
        else {
            relativeUrl += "noiframe=true";
        }
        this.setValue(relativeUrl);
        
        return super.doStartTag();
    }

}