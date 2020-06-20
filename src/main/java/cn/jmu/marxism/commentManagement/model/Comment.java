package cn.jmu.marxism.commentManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/19 20:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    //评论的课件
    private String filename;
    //评论的用户名
    private String username;
    //评论的内容
    private String content;
    //评论的时间
    private String time;
}
