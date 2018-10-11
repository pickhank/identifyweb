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
package com.woodare.framework.filter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * 
 * ClassName: ResponseCorsFilter
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class ResponseCorsFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {

        ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
        resp.header("Access-Control-Allow-Origin", "*").header("Access-Control-Max-Age", 1728000).header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        String reqHead = req.getHeaderValue("Access-Control-Request-Headers");
        if (null != reqHead && !reqHead.equals(null)) {
            resp.header("Access-Control-Allow-Headers", reqHead);
        }

        contResp.setResponse(resp.build());
        return contResp;

    }

}
