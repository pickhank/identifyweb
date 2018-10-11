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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

import com.woodare.core.util.SDFFactory;

/**
 * 
 * ClassName: FileCommonUtils
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class FileCommonUtils {
    private static Logger log = Logger.getLogger(FileCommonUtils.class);

    /**
     * 
     * @param fileId
     * @param publishFlag
     * @return
     */
    public static String getFileFullName(String fileName, String fileType) {
        return fileName + "." + fileType;
    }

    /**
     * 
     * @param fileId
     * @param publishFlag
     * @return
     */
    public static String getFileExt(String fileName) {
        if (fileName != null && fileName.indexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }

    /**
     * Utility to get fully qualified path based on a parent directory if
     * relative, else absolute is returned.
     * 
     * @param parentPath
     *            Parent dir
     * @param childPath
     *            Path string
     * 
     * @return String with path as child of baseDirectory if path was relative.
     *         If it was already absolute, same string is returned.
     */
    public static String getAbsolutePath(String childPath) {
        File childPathFile = new File(childPath);

        if (childPathFile.isAbsolute()) {
            return childPath;
        }

        childPath = new File(SysProperties.getInstance().getProperty("root.uploadfiles.valid"), childPath).toString();

        return new File(SysProperties.getInstance().getBaseDirectory(), childPath).toString();
    }

    /**
     * 
     * @param relativePath
     * @return
     */
    public static String getResourcePath(String relativePath) {
        File childPathFile = new File(relativePath);

        if (childPathFile.isAbsolute()) {
            return relativePath;
        }
        return new File(SysProperties.getInstance().getBaseDirectory(), relativePath).toString();
    }

    public static String generateFilename(String originalFilename) {

        String filePre = SDFFactory.MONTH.format(new Date());

        String fileExt = "";
        int lastIndex = originalFilename.lastIndexOf('.');
        // 取得文件的扩展名
        if (lastIndex != -1) {
            fileExt = originalFilename.substring(lastIndex);
        }

        String filename = filePre + "/" + CommonUtils.uuid() + fileExt;

        return filename;
    }

    /**
     * 
     * @param parentPath
     * @param childPath
     * @return
     */
    public static String getPathWithParent(String parentPath, String childPath) {
        File childPathFile = new File(childPath);

        if (childPathFile.isAbsolute()) {
            return childPath;
        }

        File pathWithParentFile = new File(parentPath, childPath);
        return pathWithParentFile.getAbsolutePath();
    }

    /**
     * Utility to get fully qualified path based on a parent directory if
     * relative, else absolute is returned.
     * 
     * @param parentPath
     *            Parent dir
     * @param childPath
     *            Path string
     * 
     * @return String with path as child of baseDirectory if path was relative.
     *         If it was already absolute, same string is returned.
     */
    public static String getUploadsPath(String parentPath, String childPath) {
        String fullPath = null;

        File childPathFile = new File(childPath);

        if (childPathFile.isAbsolute()) {
            return childPath;
        }

        if (StringUtils.isNotEmpty(parentPath)) {
            File pathWithParentFile = new File(parentPath, childPath);
            String pathWithParent = pathWithParentFile.toString();
            if (pathWithParentFile.isAbsolute()) {
                fullPath = pathWithParent;
            } 
            else {
            	String uploadDir = SysProperties.getInstance().getProperty("uploads.dir");
            	if(StringUtils.isEmpty(uploadDir)) {
            		uploadDir = SysProperties.getInstance().getBaseDirectory();
            	}
                fullPath = new File(uploadDir, pathWithParent).toString();
            }
        } else {
        	String uploadDir = SysProperties.getInstance().getProperty("uploads.dir");
        	if(StringUtils.isEmpty(uploadDir)) {
        		uploadDir = SysProperties.getInstance().getBaseDirectory();
        	}
            fullPath = new File(uploadDir, childPath).toString();
        }

        return fullPath;
    }
    /**
     * Utility to get fully qualified path based on a parent directory if
     * relative, else absolute is returned.
     * 
     * @param parentPath
     *            Parent dir
     * @param childPath
     *            Path string
     * 
     * @return String with path as child of baseDirectory if path was relative.
     *         If it was already absolute, same string is returned.
     */
    public static String getFullPath(String parentPath, String childPath) {
        String fullPath = null;

        File childPathFile = new File(childPath);

        if (childPathFile.isAbsolute()) {
            return childPath;
        }

        if (StringUtils.isNotEmpty(parentPath)) {
            File pathWithParentFile = new File(parentPath, childPath);
            String pathWithParent = pathWithParentFile.toString();
            if (pathWithParentFile.isAbsolute()) {
                fullPath = pathWithParent;
            } else {
                fullPath = new File(SysProperties.getInstance().getBaseDirectory(), pathWithParent).toString();
            }
        } else {
            fullPath = new File(SysProperties.getInstance().getBaseDirectory(), childPath).toString();
        }

        return fullPath;
    }

    /**
     * 
     * @param path
     * @param fileName
     * @param input
     * @return
     * @throws IOException
     */
    public static void writeFile(String filePath, InputStream input) throws IOException {
        log.debug(String.format("Begin to save file(%s) to folder(s), with file name ", filePath));

        File targetFile = new File(filePath);

        makeDir(targetFile.getParentFile());

        try {
            if (!targetFile.exists()) {
                Streams.copy(input, new BufferedOutputStream(new FileOutputStream(targetFile)), true);
                log.info("Succeed to save file: " + filePath);
            } else {
                throw new IOException(String.format("The file[%s] should not be existed!", filePath));
            }
        } catch (IOException e) {
            // To remove failed file
            if (targetFile != null && targetFile.exists()) {
                // log.error(String.format("Failed to delete the bad file[%s]",
                // newFile.getAbsolutePath()));
                if (!targetFile.delete()) {
                    log.warn(String.format("Failed to delete the bad file[%s], please manually removed it!", targetFile.getAbsolutePath()));
                }
            }
            throw e;
        }
    }

    /**
     * 
     * @param sourceFilePath
     * @param targetDirectory
     * @throws IOException
     */
    public static void moveFile(String sourceFilePath, String targetFilePath) throws IOException {
        org.apache.commons.io.FileUtils.moveFile(new File(sourceFilePath), new File(targetFilePath));
    }

    /**
     * 
     * @param srcFile
     * @param destDir
     * @throws IOException
     */
    public static void copyToFile(File srcFile, File destFile) throws IOException {
        org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * 
     * @param dir
     */
    public static void makeDir(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 
     * @param path
     * @param fileName
     * @param input
     * @return
     * @throws IOException
     */
    public static void writeFile(String folder, String fileName, InputStream input) throws IOException {
        log.debug(String.format("Begin to save file(%s) to folder(%s), with file name ", fileName, folder));
        writeFile(getPathWithParent(folder, fileName), input);
    }

}
