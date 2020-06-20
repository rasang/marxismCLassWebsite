package cn.jmu.marxism.mapper;

import cn.jmu.marxism.courseIntroduceManagement.model.CourseIntroduce;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/18 21:03
 */
@Repository
public interface CourseIntroduceMapper {
    /**
     * 根据id获取课程简介
     * @param id 课程简介id
     * @return courseIntrduce对象
     */
    @Select("select * from courseIntroduce where id=#{id}")
    public CourseIntroduce getCourseIntroduce(@Param("id") int id);

    /**
     * 更改课程简介
     * @param summary 课程概要
     * @param materials 课程资料
     * @param characteristics 教学特色
     * @param conditions 教学条件
     * @param environment 教学环境
     * @param id 课程简介id
     * @return 更改的结果
     */
    @Update("update courseIntroduce set course_summary=#{summary},course_materials=#{materials},teaching_characteristics=#{characteristics},teaching_conditions=#{conditions},teaching_environment=#{environment} where id=#{id}")
    public int update(@Param("summary") String summary,@Param("materials") String materials,@Param("characteristics") String characteristics,@Param("conditions") String conditions,@Param("environment") String environment,@Param("id") int id);
}
