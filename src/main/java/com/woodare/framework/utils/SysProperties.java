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
package com.woodare.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

/**
 * 
 * ClassName: SysProperties
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class SysProperties {

    private static Logger log = Logger.getLogger(SysProperties.class);

    /** Singleton local property instance **/
    private static Properties SysLocalPropObject = null;

    /** Property file default **/
    private static String defaultpropfilename = "/system.properties";

    protected long lastModifiedData = -1;

    private static SysProperties instance;

    private String baseDirectory = null;

    private boolean loadFromDatabase = false;

    private static Object obj1 = new Object();
    private static Object obj2 = new Object();
    
    public static SysProperties getInstance() {
        if (instance == null) {
        	synchronized(obj1) {
        		if(instance == null) {
                	synchronized(obj2) {
                		instance = new SysProperties();
                	}
        		}
        	}
        }
        return instance;
    }

    public static SysProperties getInstance(ServletContext ctx) {
        instance = new SysProperties(ctx);
        return instance;
    }

    /**
     * 
     * @param ctx
     */
    private SysProperties(ServletContext ctx) {
        SysLocalPropObject = new Properties();
        final String filePath = this.getClass().getResource(defaultpropfilename).getFile();
        final SysProperties self = this;

        log.info("=============================");
        log.info("Loading data: " + filePath);
        File propertyFile = new File(filePath);
        reloadFile(propertyFile);

        if (ctx != null) {
            baseDirectory = ctx.getRealPath("/");

            // Loop to detect file changes
            Thread t = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                        try {
                            File propertyFile = new File(filePath);
                            if (self.lastModifiedData != propertyFile.lastModified()) {
                                log.info("Property file is changed to reload!");
                                self.reloadFile(propertyFile);
                                self.lastModifiedData = propertyFile.lastModified();
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            };
            t.start();
        }
    }

    /** Private Constructor for Singleton Instance */
    private SysProperties() {
        this(null);
    }

    /** Get the base directory for this application */
    public String getBaseDirectory() {
        return baseDirectory == null ? "" : baseDirectory;
    }

    protected void reloadFile(File propertyFile) {
        FileInputStream inputStreamLocal = null;
        try {
            inputStreamLocal = new FileInputStream(propertyFile);
            if (inputStreamLocal != null) {
                SysLocalPropObject.load(inputStreamLocal);
            }
            String enableString = SysLocalPropObject.getProperty("database.config.enable");

            if (enableString != null) {
                loadFromDatabase = enableString.trim().equalsIgnoreCase("true") || enableString.trim().equals("1");
            } else {
                loadFromDatabase = false;
            }
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                log.info("No Local Properties File Found");
                SysLocalPropObject = null;
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                if (inputStreamLocal != null)
                    inputStreamLocal.close();
            } catch (IOException e) {
                log.info("Exception is happened when to close file stream");
            }
        }
    }
    
    /**
     * Get a property from the Properties file (uppercase Get to avoid conflict)
     */
    public String getProperty(String property) {
        String val = null;

        if (loadFromDatabase) {
            val = DatabaseProperties.getPropertyValue(property);
        }
        if (val == null && SysLocalPropObject != null)
            val = SysLocalPropObject.getProperty(property);

        return (val);
    }

    /** Get a property, allowing for a default value specification */
    public String getProperty(String property, String defaultValue) {
        String val = getProperty(property);

        if (val == null) {
            val = defaultValue;
        }

        return (val);
    }

    /**
     * Get a property from the Properties file (uppercase Get to avoid conflict)
     */
    public Integer getIntProperty(String property) {
        String val = getProperty(property);
        Integer nVal = null;
        try {
            nVal = Integer.parseInt(val);
        } catch (Exception e) {

        }
        return nVal;

    } // getProperty()

    /** Get a property, allowing for a default value specification */
    public Integer getIntProperty(String property, Integer defaultValue) {
        Integer val = getIntProperty(property);

        if (val == null) {
            val = defaultValue;
        }

        return (val);
    }

    /**
     * Get a property from the Properties file (uppercase Get to avoid conflict)
     */
    public Long getLongProperty(String property) {
        String val = getProperty(property);
        Long nVal = null;
        try {
            nVal = Long.parseLong(val);
        } catch (Exception e) {

        }
        return nVal;

    }

    /** Get a property, allowing for a default value specification */
    public Long getLongProperty(String property, Long defaultValue) {
        Long val = getLongProperty(property);

        if (val == null) {
            val = defaultValue;
        }

        return (val);
    }

    public boolean getBooleanProperty(String property, boolean defaultValue) {
        boolean retval = false;
        String val = getProperty(property);

        if (val == null || val.equals(""))
            retval = defaultValue;
        else if (val.trim().equalsIgnoreCase("true") || val.trim().equals("1"))
            retval = true;

        return (retval);
    }
}
