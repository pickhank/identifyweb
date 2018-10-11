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
package com.woodare.core.web.startup;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woodare.framework.utils.SysProperties;

/**
 * 
 * @author lu_feng
 * 
 */
public class Initializer extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    public static boolean runAlready = false;

    /**
     * Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (runAlready) {
            System.err.println("Initializer already run, exiting init()");
            return;
        }
        runAlready = true;

        // Now, set up the application's config file.
        SysProperties.getInstance(config.getServletContext());
        System.out.println("Initializer started properties in: " + SysProperties.getInstance().getBaseDirectory());
    }

    /**
     * Destroys the servlet.
     */
    public void destroy() {
        System.err.println("Initializer servlet destroyed.");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.close();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * 
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * 
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
}
