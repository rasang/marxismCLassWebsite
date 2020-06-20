package cn.jmu.marxism.fileUploadManagement.controller;


import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.fileUploadManagement.model.FileNameUrl;
import cn.jmu.marxism.fileUploadManagement.service.FileUrlToDBService;
import cn.jmu.marxism.fileUploadManagement.service.UploadLearnFileService;
import cn.jmu.marxism.fileUploadManagement.service.UploadTeachFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UploadFileController {

    //承接返回值
    ArrayList<String> end =new ArrayList<>();

    @Autowired
    private FileUrlToDBService fileUrlToDBService;

    //上传教案文件uploadTeachFile
    @RequestMapping(value = "/uploadTeachFile", produces = "application/json;charset=UTF-8")
    public ResponseBody uploadTeachFile(@RequestParam("fileName") MultipartFile file) {

        //承接文件上传结果返回值
        end = UploadTeachFileService.UploadTeachFile(file);

        if (end.get(0).equals("404")){
            return new ResponseBody("404","教案文件为空",null);
        }else if(end.get(0).equals("200")){
            //存入数据库表teachfileurl
            fileUrlToDBService.insertTeachFileUrl(end.get(1),end.get(2));

            return new ResponseBody("200","教案文件上传成功",null);
        }else if(end.get(0).equals("403")){
            return new ResponseBody("403","教案文件格式不符合要求",null);
        }else{
            return new ResponseBody("-6","教案文件上传失败",null);
        }

    }

    //上传课件文件uploadLearnFile
    @RequestMapping(value = "/uploadLearnFile", produces = "application/json;charset=UTF-8")
    public ResponseBody uploadLearnFile(@RequestParam("fileName") MultipartFile file){

        //承接文件上传结果返回值
        end = UploadLearnFileService.UploadLearnFile(file);

        if (end.get(0).equals("404")){
            return new ResponseBody("404","课件文件为空",null);
        }else if(end.get(0).equals("200")){
            //存入数据库表learnfileurl
            fileUrlToDBService.insertLearnFileUrl(end.get(1),end.get(2));
            return new ResponseBody("200","课件文件上传成功",null);
        }else if(end.get(0).equals("403")){
            return new ResponseBody("403","课件文件格式不符合要求",null);
        }else{
            return new ResponseBody("-6","课件文件上传失败",null);
        }
    }


    //查询教案表
    @RequestMapping(value = "/searchTeachFile", produces = "application/json;charset=UTF-8")
    public List<FileNameUrl> searchTeachFile(){
        List<FileNameUrl> end=fileUrlToDBService.selectTeachFileUrl();
        System.out.print("查询到的教案文件数量=="+end.size()+"\n");
        System.out.print(end);
        return end;
    }

    //查询课件表
    @RequestMapping(value = "/searchLearnFile", produces = "application/json;charset=UTF-8")
    public List<FileNameUrl> searchLearnFile(){
        List<FileNameUrl> end=fileUrlToDBService.selectLearnFileUrl();
        System.out.print("查询到的课件文件数量=="+end.size()+"\n");
        System.out.print(end);
        return end;
    }
}

