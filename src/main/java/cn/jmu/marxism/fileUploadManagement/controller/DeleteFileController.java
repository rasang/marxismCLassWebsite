package cn.jmu.marxism.fileUploadManagement.controller;

import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.common.annotation.TeacherOnly;
import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.fileUploadManagement.service.DeleteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qbz
 * @version 1.0
 * @date 2020/6/22 19:43
 */
@RequireToken
@TeacherOnly
@RestController
public class DeleteFileController {
    @Autowired
    DeleteFileService deleteFileService;
    /**
     * 通过此api进行删除文件
     * @param fileName
     * @param type
     */

    @RequestMapping("/delete/{type}/{fileName}")
    public ResponseBody deleteFile(@PathVariable("type") String type, @PathVariable("fileName") String fileName){
        return deleteFileService.deleteFile(type, fileName);
    }
}
