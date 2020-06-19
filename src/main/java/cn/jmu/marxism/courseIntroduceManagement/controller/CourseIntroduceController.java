package cn.jmu.marxism.courseIntroduceManagement.controller;

import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.common.annotation.TeacherOnly;
import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.courseIntroduceManagement.service.CourseIntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/18 22:20
 */
@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
public class CourseIntroduceController {
    @Autowired
    CourseIntroduceService courseIntroduceService;

    /**
     * 获取课程简介api
     * @return 响应体，状态码成功为200，失败为403
     */
    @RequestMapping(value = "/courseIntroduce",method = RequestMethod.GET)
    @RequireToken
    public ResponseBody getCourseIntroduce(){
        return courseIntroduceService.getCourseIntroduce(1);
    }

    /**
     * 修改课程简介api
     * @param summary
     * @param materials
     * @param characteristics
     * @param conditions
     * @param environment
     * @return 响应体，状态码成功为200，失败为403
     */
    @RequestMapping(value = "/courseIntroduce",method = RequestMethod.POST)
    @TeacherOnly
    public ResponseBody update(String summary, String materials, String characteristics, String conditions, String environment){
        return courseIntroduceService.update(summary,materials,characteristics,conditions,environment,1);
    }
}
