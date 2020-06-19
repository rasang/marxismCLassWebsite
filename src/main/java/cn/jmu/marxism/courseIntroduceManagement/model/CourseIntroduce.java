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
    private String course_summary;
    private String course_materials;
    private String teaching_characteristics;
    private String teaching_conditions;
    private String teaching_environment;
}
