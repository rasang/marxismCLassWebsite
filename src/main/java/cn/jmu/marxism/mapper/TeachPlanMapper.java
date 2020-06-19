package cn.jmu.marxism.mapper;

import cn.jmu.marxism.teachPlanManagement.model.TeachPlan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xingkyh
 * @version 1.0
 * @data 2020/6/19 14:28
 */
@Repository
public interface TeachPlanMapper {
    /**
     * 根据id获取教学大纲
     * @param id
     * @return TeachPlan对象
     */
    @Select("select * from teachPlan where id=#{id}")
    public TeachPlan getTeachPlan(@Param("id") int id);

    /**
     * 修改教学大纲
     * @param teachPlan
     * @return 修改的结果（修改是否成功）
     */
    @Update("update teachPlan set content=#{content},objective=#{objective},arrangement=#{arrangement},assessment=#{assessment} where id=#{id}")
    public int update(TeachPlan teachPlan);
}
