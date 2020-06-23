package cn.jmu.marxism.fileUploadManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jmu.marxism.fileUploadManagement.service.DownloadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qbz
 * @version 1.0
 * @date 2020/6/21 11:31
 */
@RestController
public class DownloadController {
    @Autowired
    DownloadService downloadService;

    /**
     * 通过此api进行文件下载
     * @param fileName
     * @param type
     * @param response
     * @param request
     */
    @RequestMapping("/download/{type}/{fileName}")
    public void download(@PathVariable("fileName") String fileName, @PathVariable("type") String type,
                         HttpServletResponse response, HttpServletRequest request){
        downloadService.download(type, fileName, response, request);
    }
}
