package cn.jmu.marxism.teachPlanManagement.controller;

import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.common.annotation.TeacherOnly;
import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.teachPlanManagement.model.TeachPlan;
import cn.jmu.marxism.teachPlanManagement.service.TeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
public class TeachPlanController {
    @Autowired
    TeachPlanService teachPlanService;

    /**
     * 获取教学大纲
     * @return 响应体，状态码成功为200，失败为403
     */
    @RequestMapping(value = "/teachPlan",method = RequestMethod.GET)
    @RequireToken
    public ResponseBody getTeachPlan(){
        return teachPlanService.getTechPlan(1);
    }

    /**
     * 修改教学大纲
     * @param teachPlan
     * @return 响应体，状态码成功为200，失败为403
     */
    @RequestMapping(value = "/teachPlan",method = RequestMethod.POST)
    @RequireToken
    @TeacherOnly
    public ResponseBody update(TeachPlan teachPlan){
        teachPlan.setId(1);
        return teachPlanService.update(teachPlan);
    }
}
