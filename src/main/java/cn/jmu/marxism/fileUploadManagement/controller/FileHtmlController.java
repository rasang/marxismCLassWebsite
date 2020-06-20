package cn.jmu.marxism.fileUploadManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileHtmlController {

    @RequestMapping("/fileL")
    public String fileL(){
        System.out.print("跳转fileL课件上传页面"+"\n");
        return "fileL";
    }

  @RequestMapping("/fileT")
    public String fileT(){
        System.out.print("跳转fileT教案上传页面"+"\n");
        return "fileT";
    }

}
