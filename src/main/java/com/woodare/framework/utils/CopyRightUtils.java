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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.woodare.core.util.SDFFactory;

/**
 * 
 * ClassName: CopyRightUtils
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class CopyRightUtils {
    private static String[] BASE_DIR = new String[] { "D:/works/trunk/cas-service/src/main/java", "D:/works/trunk/data-dynamic-service/src/main/java",
            "D:/works/trunk/data-dynamic-service-impl/src/main/java", "D:/works/trunk/data-dynamic-service-interface/src/main/java",
            "D:/works/trunk/data-static-service/src/main/java",

            "D:/works/trunk/data-static-service-impl/src/main/java", "D:/works/trunk/data-static-service-interface/src/main/java",
            "D:/works/trunk/data-union-service/src/main/java", "D:/works/trunk/framework/src/main/java", "D:/works/trunk/kpyx-plugin/src/main/java",
            "D:/works/trunk/logical-service/src/main/java"

    };
    private static String[] DIST_DIR = new String[] { "D:/works/trunk/cas-service/src/main/java1", "D:/works/trunk/data-dynamic-service/src/main/java1",
            "D:/works/trunk/data-dynamic-service-impl/src/main/java1", "D:/works/trunk/data-dynamic-service-interface/src/main/java1",
            "D:/works/trunk/data-static-service/src/main/java1",

            "D:/works/trunk/data-static-service-impl/src/main/java1", "D:/works/trunk/data-static-service-interface/src/main/java1",
            "D:/works/trunk/data-union-service/src/main/java1", "D:/works/trunk/framework/src/main/java1", "D:/works/trunk/kpyx-plugin/src/main/java1",
            "D:/works/trunk/logical-service/src/main/java1"

    };

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < BASE_DIR.length; i++) {
            File base = new File(BASE_DIR[i]);
            File dist = new File(DIST_DIR[i]);
            if (!dist.exists()) {
                dist.mkdirs();
            } else {
                dist.delete();
            }
            execute(base, dist);
        }

    }

    private static void execute(File srcFile, File distFile) throws Exception {
        if (srcFile.isDirectory()) {
            File[] files = srcFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                File subFile = files[i];
                String srcPath = subFile.getName();
                File subDistFile = new File(distFile.getAbsolutePath() + File.separator + srcPath);
                if (subFile.isDirectory()) {
                    if (!subDistFile.exists()) {
                        subDistFile.mkdirs();
                    }
                    execute(subFile, subDistFile);
                } else if (subFile.getName().endsWith("java")) {
                    addCopyright(subFile, subDistFile);
                }
            }
        } else {
            addCopyright(srcFile, distFile);
        }
    }

    private static void addCopyright(File srcFile, File distFile) throws Exception {
        System.out.println(srcFile.getAbsolutePath() + " => " + distFile.getAbsolutePath());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(distFile)));
        List<String> lines = new ArrayList<String>();
        String temp = null;
        String fullName = null;
        boolean validLine = false;
        while ((temp = br.readLine()) != null) {
            if (temp.trim().startsWith("package")) {
                validLine = true;
            }
            if (validLine) {
                lines.add(temp);
            }
        }

        bw.write(getFileHeader(fullName, SDFFactory.DATETIME_DASH.format(new Date(srcFile.lastModified()))));
        bw.write("\r\n");
        for (String line : lines) {
            bw.write(line);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }

    private static String getFileHeader(String fullName, String createDate) {
        StringBuffer sb = new StringBuffer();
        sb.append("/******************************************************************************\n"
                + " *                                                                             \n"
                + " *                      Woodare PROPRIETARY INFORMATION                        \n"
                + " *                                                                             \n"
                + " *          The information contained herein is proprietary to Woodare         \n"
                + " *           and shall not be reproduced or disclosed in whole or in part      \n"
                + " *                    or used for any design or manufacture                    \n"
                + " *              without direct written authorization from FengDa.              \n"
                + " *                                                                             \n"
                + " *            Copyright (c) 2013 by Woodare.  All rights reserved.             \n"
                + " *                                                                             \n"
                + " *****************************************************************************/");
        return sb.toString();
    }
}
