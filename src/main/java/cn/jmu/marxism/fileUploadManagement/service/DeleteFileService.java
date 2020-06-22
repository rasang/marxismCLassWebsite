package cn.jmu.marxism.fileUploadManagement.service;

import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.mapper.FileUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/22 19:44
 */
@Service
public class DeleteFileService {
    @Autowired
    FileUrlMapper fileUrlMapper;

    public ResponseBody deleteFile(String type, String fileName){
        String jarPath=System.getProperty("user.dir");
        String transJarPath=jarPath.replace("\\","/");
        File file = new File(transJarPath + "/" + type + "/" + fileName);
        if(file.isFile()){
            file.delete();
            if(type.equals("learnFile")){
                fileUrlMapper.deleteLearn(fileName);
            }
            else{
                fileUrlMapper.deleteTeach(fileName);
            }
            return new ResponseBody("200","删除成功",null);
        }
        else{
            return new ResponseBody("404","无此文件",null);
        }
    }
}
