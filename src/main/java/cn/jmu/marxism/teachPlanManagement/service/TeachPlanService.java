package cn.jmu.marxism.teachPlanManagement.service;

import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.mapper.TeachPlanMapper;
import cn.jmu.marxism.teachPlanManagement.model.TeachPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xingkyh
 * @version 1.0
 * @data 2020/6/19 14:48
 */
@Service
public class TeachPlanService {
    @Autowired
    private TeachPlanMapper teachPlanMapper;

    /**
     * 获取教学大纲
     * @param id
     * @return 包含获取结果的响应体
     */
    public ResponseBody getTechPlan(int id){
        TeachPlan teachPlan=teachPlanMapper.getTeachPlan(id);
        if(teachPlan==null){
            return new ResponseBody("403","获取失败","null");
        }else {
            return new ResponseBody("200","获取成功",teachPlan);
        }
    };

    /**
     * 修改教学大纲
     * @param teachPlan
     * @return 包含修改结果的响应体
     */
    public ResponseBody update(TeachPlan teachPlan){
        int result=teachPlanMapper.update(teachPlan);
        if(result==1){
            return new ResponseBody("200","修改成功",null);
        }else {
            return new ResponseBody("403","修改失败",null);
        }
    }
}
