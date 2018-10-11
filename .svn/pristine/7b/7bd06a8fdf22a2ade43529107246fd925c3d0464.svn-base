package com.thirdparty.passway.dsp.cpcn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * lixianghui      2017年7月20日       
 * </pre>
 */
public final class SystemEnvironment {

    private static Logger logger = Logger.getLogger("system");
    
    public static Map<String,String> INDUSTRY_DIC = new HashMap<String,String>();
    
    public static List<String> INDUSTRY_LIST = new ArrayList<String>() ;
    
    public static String dicConfigPath;
    
    public static final String SYS_INDUSTRY_DIC_FILE = "industryCategory.dic";
    
    private SystemEnvironment(){
        super();
    }
    
    public static synchronized void initIndustry(String dicPath) throws Exception{
        
        dicConfigPath = dicPath;
        String sysConfigFile = dicConfigPath + File.separatorChar + SYS_INDUSTRY_DIC_FILE;
        logger.info(sysConfigFile);
        
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(sysConfigFile));
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        INDUSTRY_DIC.clear();
        INDUSTRY_LIST.clear();
        Enumeration<?> enu = properties.propertyNames();
        while (enu.hasMoreElements()) {
            String code = (String) enu.nextElement();
            String name = (String) properties.get(code);
            code = new String(code.getBytes("ISO8859-1"),"UTF-8");
            name = new String(name.getBytes("ISO8859-1"),"UTF-8");
            INDUSTRY_DIC.put(code, name);
            INDUSTRY_LIST.add(code);
        }
        Collections.sort(INDUSTRY_LIST);
    }
    
    
}

