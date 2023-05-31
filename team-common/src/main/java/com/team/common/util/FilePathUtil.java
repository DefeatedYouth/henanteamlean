package com.team.common.util;

import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

public class FilePathUtil {





    //文件上传
    public static String addFilePath(MultipartFile file,String suffix,String appointPath) {

            //通过UUID生成唯一文件名
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
            String picturePath = appointPath + filename;     //文件访问路径
            //获取项目classes/的地址
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            String savePath = path + "static/"+picturePath;  //文件保存路径


            File savePathFile = new File(savePath);
            if (!savePathFile.exists()) {
                //若不存在该目录，则创建目录
                savePathFile.getParentFile().mkdirs();
            }

            try {
                file.transferTo(savePathFile);  //将临时存储的文件移动到真实存储路径下
            } catch (Exception e) {
                e.printStackTrace();
                new Exception("保存文件异常");
            }
        return picturePath;
    }

    //文件删除
    public static void deleteFile(List<String> deletePathList){
        //获取项目classes/static的地址
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        for (String deletePath : deletePathList) {
            new File(path + deletePath).delete();
        }
    }


    //文件转PDF在线预览
    public static void viewByPDF(String filePath,String prePDF,HttpServletResponse response,DocumentConverter converter){
        File file = new File(filePath);//需要转换的文件

        try {
            File newFile = new File(prePDF);//转换之后文件生成的地址
            if (!newFile.exists()) {
                newFile.getParentFile().mkdirs();
            }
            //文件转化
            converter.convert(file).to(new File(prePDF+"view.pdf")).execute();
            //使用response,将pdf文件以流的方式发送的前端
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream in = new FileInputStream(new File(prePDF+"view.pdf"));// 读取文件
            // 如果源文件就是 pdf，不要转换
            //InputStream in = new FileInputStream(new File("E:/testFile/temp.pdf"));// 读取文件
            // copy文件
            int i = IOUtils.copy(in, outputStream);
            in.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //文件下载
    public static void download(String filePath,HttpServletResponse response){
        try {
             // path是指想要下载的文件的路径
            File file = new File(filePath);
             // 获取文件名
            String filename = file.getName();
             // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

             // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
             // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
