package com.woodare.core.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.woodare.core.jpa.model.Attachment;
import com.woodare.core.jpa.service.IAttachmentDAO;
import com.woodare.core.util.ImageCommonUtils;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.RequestUtil;

/**
 * 附件文件下载
 * 
 */
public class FileDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext application = ApplicationContextHolder.getApplicationContext();
        IAttachmentDAO attachmentDAO = application.getBean(IAttachmentDAO.class);
        
        String fileId = req.getParameter("fileId");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (StringUtils.isNotEmpty(fileId)) {
            Attachment fileAttach = attachmentDAO.findOne(fileId);
            resp.setContentType(RequestUtil.getContentType(fileAttach.getExt()));
            ServletOutputStream out = null;

            java.io.FileInputStream fileIn = null;
            try {
                String absoluteFilePath = ImageCommonUtils.getAbsolutPathWithPrefix(fileAttach.getPath());
                
                fileIn = new java.io.FileInputStream(absoluteFilePath);
                resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileAttach.getName(), "utf-8"));

                out = resp.getOutputStream();

                byte[] buff = new byte[1024];
                int leng = fileIn.read(buff);
                while (leng > 0) {
                    out.write(buff, 0, leng);
                    leng = fileIn.read(buff);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (fileIn != null) {
                    try {
                        fileIn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
