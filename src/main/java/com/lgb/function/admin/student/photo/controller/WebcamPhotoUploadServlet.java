package com.lgb.function.admin.student.photo.controller;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class WebcamPhotoUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * 处理学员自己拍照上传的照片
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        // 获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 获取文件需要上传到的路径
        String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/photo/");

        // 如果文件夹不存在 将创建文件夹
        if (!new File(savePath).exists()) {
            new File(savePath).mkdirs();
        }

        factory.setRepository(new File(savePath));
        factory.setSizeThreshold(1024 * 1024);

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                String name = item.getFieldName();

                if (item.isFormField()) {
                    String imageData = item.getString();

                    BASE64Decoder decoder = new BASE64Decoder();
                    OutputStream out = null;

                    try {
                        byte[] bytes = decoder.decodeBuffer(imageData);
                        String newImageName = savePath + "/" + new DateTime().toString("MM-dd-yyyy-HH-mm-ss-SSS");
                        out = new FileOutputStream(newImageName + ".jpg");
                        out.write(bytes);

                        out.flush();
                        out.close();
                        ajaxJsonReturn(response, newImageName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    String newFileName = savePath + "/" + new DateTime().toString("MM-dd-yyyy-HH-mm-ss-SSS");
                    request.setAttribute(name, newFileName);
                    try {
                        OutputStream out = new FileOutputStream(new File(savePath, newFileName + ".jpg"));
                        InputStream in = item.getInputStream();
                        int length = 0;
                        byte[] buf = new byte[1024];
                        while ((length = in.read(buf)) != -1) {
                            out.write(buf, 0, length);
                        }

                        in.close();
                        out.close();
                        ajaxJsonReturn(response, newFileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ajaxJsonReturn(HttpServletResponse response, String newImageName) {
        PrintWriter writer = null;
        try {
            Gson gson = new Gson();
            String fileName = newImageName.substring(newImageName.lastIndexOf("/") + 1);
            String json = gson.toJson(fileName);
            writer = response.getWriter();
            writer.print(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
