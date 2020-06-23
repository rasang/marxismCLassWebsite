package cn.jmu.marxism.fileUploadManagement.controller;


import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.common.annotation.TeacherOnly;
import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.fileUploadManagement.model.FileNameUrl;
import cn.jmu.marxism.fileUploadManagement.service.FileUrlToDBService;
import cn.jmu.marxism.fileUploadManagement.service.UploadLearnFileService;
import cn.jmu.marxism.fileUploadManagement.service.UploadTeachFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qbz
 * @version 1.0
 * @date 2020/6/18 11:31
 */
@RestController
public class UploadFileController {

    @Autowired
    private FileUrlToDBService fileUrlToDBService;

    ArrayList<String> end =new ArrayList<>();

    /**
     * 上传教案文件api，根据前端提交的文件上传使用的教案文件
     * @param file 教案课件
     * @return 响应体，状态码成功为200，课件为空为404，课件上传失败为-6，教案格式不符合要求为403
     */
    @RequestMapping(value = "/TeachFile", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @RequireToken
    @TeacherOnly
    public ResponseBody uploadTeachFile(@RequestParam("fileName") MultipartFile file) {
        end = UploadTeachFileService.UploadTeachFile(file);
        if (end.get(0).equals("404")){
            return new ResponseBody("404","教案文件为空",null);
        }else if(end.get(0).equals("200")){
            fileUrlToDBService.insertTeachFileUrl(end.get(1),end.get(2));
            return new ResponseBody("200","教案文件上传成功",null);
        }else if(end.get(0).equals("403")){
            return new ResponseBody("403","教案文件格式不符合要求",null);
        }else{
            return new ResponseBody("-6","教案文件上传失败",null);
        }
    }

    /**
     * 上传课件文件api，根据前端提交的文件上传使用的课件文件
     * @param file 课件文件
     * @return 响应体，状态码成功为200，课件为空为404，课件上传失败为-6，教案格式不符合要求为403
     */
    @RequestMapping(value = "/LearnFile", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @RequireToken
    @TeacherOnly
    public ResponseBody uploadLearnFile(@RequestParam("fileName") MultipartFile file){
        end = UploadLearnFileService.UploadLearnFile(file);
        if (end.get(0).equals("404")){
            return new ResponseBody("404","课件文件为空",null);
        }else if(end.get(0).equals("200")){
            fileUrlToDBService.insertLearnFileUrl(end.get(1),end.get(2));
            return new ResponseBody("200","课件文件上传成功",null);
        }else if(end.get(0).equals("403")){
            return new ResponseBody("403","课件文件格式不符合要求",null);
        }else{
            return new ResponseBody("-6","课件文件上传失败",null);
        }
    }

    /**
     * 教案表中提取url地址api
     * @return 包含获取结果的响应体，状态码查询成功为200，data字段为数据表数组List-end
     */
    @RequireToken
    @RequestMapping(value = "/TeachFile", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResponseBody searchTeachFile(){
        List<FileNameUrl> end=fileUrlToDBService.selectTeachFileUrl();
        return new ResponseBody("200","查询成功",end);
    }

    /**
     * 课件表中提取url地址api
     * @return 包含获取结果的响应体，状态码查询成功为200，data字段为数据表数组List-end
     */
    @RequireToken
    @RequestMapping(value = "/LearnFile", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResponseBody searchLearnFile(){
        List<FileNameUrl> end=fileUrlToDBService.selectLearnFileUrl();
        return new ResponseBody("200","查询成功",end);
    }
}

