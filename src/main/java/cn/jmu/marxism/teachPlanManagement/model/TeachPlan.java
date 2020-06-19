package cn.jmu.marxism.teachPlanManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xingkyh
 * @version 1.0
 * @data 2020/6/19 14:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachPlan {
    private int id;
    //教学内容
    private String content;
    //目标要求
    private String objective;
    //学时安排
    private String arrangement;
    //考核方式
    private String assessment;
}
