package cn.jmu.marxism.userManagement.controller;

import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 10:57
 * 用来测试的
 */
@RestController
public class testController {
    @Autowired
    UserService userService;
    @RequireToken
    @RequestMapping("/test")
    public HashMap test(HttpServletRequest request){
        HashMap hashMap = new HashMap();
        hashMap.put("test1",request.getAttribute("identification"));
        return hashMap;
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        String jarPath=System.getProperty("user.dir");
        String transJarPath=jarPath.replace("\\","/");
        transJarPath += "/learnFile/" + "2cdaa4d7-245c-4f17-8c1c-16f5b2273630.pdf";
        File file = new File(transJarPath);
        try {
            response.setContentType("application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode("2cdaa4d7-245c-4f17-8c1c-16f5b2273630.pdf","utf8"));
            byte[] buffer = new byte[1024];
            OutputStream os = null;
            try(FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);){
                os = response.getOutputStream();
                int i = bufferedInputStream.read(buffer);
                while (i != -1){
                    os.write(buffer);
                    i = bufferedInputStream.read(buffer);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
