package cn.jmu.marxism.fileUploadManagement.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author qbz
 * @version 1.0
 * @date 2020/6/21 11:33
 */
@Service
public class DownloadService {
    /**
     * 下载文件业务
     * @param type
     * @param fileName
     * @param response
     * @param request
     */
    public static void download(String type, String fileName, HttpServletResponse response, HttpServletRequest request){
        String path = System.getProperty("user.dir").replace("\\","/") + "/"+type + "/" + fileName;
        String MIMEType = "";
        String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (suffix.equals(".ppt")){
            MIMEType = "application/vnd.ms-powerpoint";
        }
        else if(suffix.equals(".pptx")){
            MIMEType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        }
        else if(suffix.equals(".doc")){
            MIMEType = "application/msword";
        }
        else if(suffix.equals(".docx")){
            MIMEType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        }
        File file = new File(path);
        response.setContentType(MIMEType);
        byte[] buffer = new byte[1024];
        OutputStream outputStream = null;
        try(FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);)
        {
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1){
                outputStream.write(buffer);
                i = bufferedInputStream.read(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

