package cn.jmu.marxism.courseIntroduceManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/18 20:56
 * courseIntroduce的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseIntroduce {
    private String id;
    //课程概要
    private String course_summary;
    //课程资料
    private String course_materials;
    //教学特色
    private String teaching_characteristics;
    //教学条件
    private String teaching_conditions;
    //教学环境
    private String teaching_environment;
}
