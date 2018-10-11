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
package com.woodare.framework.jersey;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.impl.WebResponseData;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.exception.UnauthorityWooException;
import com.woodare.framework.utils.JsonUtils;

/**
 * 
 * ClassName: IndexService
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
@Component
@Path("/index")
public abstract class AbstractIndexService {
    private Log log = LogFactory.getLog(AbstractIndexService.class);

    @Autowired
    private JerseyManager jerseyManager;

    public AbstractIndexService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{module}/{action}")
    public Response doVerify(@Context HttpServletRequest request, @Context HttpServletResponse httpResponse, String requestString, @PathParam("module") String module,
            @PathParam("action") String action) {

        log.debug(String.format("HTTP Request with [module = %s, action = %s, requestString = %s]", module, action, requestString));

        ResponseBuilder builder = null;

        try {
            Object result = jerseyManager.execute(request, module, action, requestString);
            WebResponseData response = new WebResponseData();
            response.setPayload(JsonUtils.toJson(result));
            response.setState(EResponseState.Successful);
            builder = Response.ok(JsonUtils.toJson(response));

            if (log.isDebugEnabled()) {
                log.debug("HTTP Response: " + JsonUtils.toJson(response));
            }
        } catch (MessageWooException e) {
            log.warn(String.format("Customer message for [module = %s, action = %s, requestString = %s]", module, action, requestString));
            WebResponseData response = new WebResponseData();
            response.setState(EResponseState.CustomMsg);
            response.setMessage(e.getMessage());
            builder = Response.ok(JsonUtils.toJson(response));

            if (log.isDebugEnabled()) {
                log.debug("HTTP Response: " + JsonUtils.toJson(response));
            }
        } catch (UnauthorityWooException e) {
            log.warn(String.format("Unauthority request for [module = %s, action = %s, requestString = %s]", module, action, requestString));

            WebResponseData response = new WebResponseData();
            response.setState(EResponseState.Unauthority);
            builder = Response.ok(JsonUtils.toJson(response));

            if (log.isDebugEnabled()) {
                log.debug("HTTP Response: " + JsonUtils.toJson(response));
            }
        } catch (Exception e) {
            log.error(String.format("Execute service (%s) error.", module), e);

            // Output all stacks to client
            WebResponseData response = new WebResponseData();
            response.setState(EResponseState.Failed);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.close();
            response.setMessage(sw.getBuffer().toString());
            builder = Response.ok(JsonUtils.toJson(response));

            if (log.isDebugEnabled()) {
                log.debug("HTTP Response: " + JsonUtils.toJson(response));
            }
        }

        return builder.build();
    }

}
