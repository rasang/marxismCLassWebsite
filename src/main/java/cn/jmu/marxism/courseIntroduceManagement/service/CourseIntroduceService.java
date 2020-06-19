package cn.jmu.marxism.courseIntroduceManagement.service;

import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.courseIntroduceManagement.model.CourseIntroduce;
import cn.jmu.marxism.mapper.CourseIntroduceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xingkyh
 * @version 1.0
 * @data 2020/6/18 21:45
 */
@Service
public class CourseIntroduceService {
    @Autowired
    private CourseIntroduceMapper courseIntroduceMapper;

    /**
     * 获取课程简介
     * @param id
     * @return 获取成功则包含课程简介的ResponseBody，失败则为null
     */
    public ResponseBody getCourseIntroduce(int id){
        CourseIntroduce courseIntroduce=courseIntroduceMapper.getCourseIntroduce(id);
        if(courseIntroduce==null){
            return new ResponseBody("403","获取失败",null);
        }else {
            return new ResponseBody("200","获取成功",courseIntroduce);
        }
    }

    /**
     * 更改课程简介
     * @param summary
     * @param materials
     * @param characteristics
     * @param conditions
     * @param environment
     * @param id
     * @return 返回包含修改结果的ResponseBody
     */
    public ResponseBody updata(String summary,String materials,String characteristics,String conditions,String environment,int id){
        int result=courseIntroduceMapper.updata(summary,materials,characteristics,conditions,environment,id);
        if(result==1){
            return new ResponseBody("200","修改成功",null);
        }else {
            return new ResponseBody("403","修改失败",null);
        }
    }
}
