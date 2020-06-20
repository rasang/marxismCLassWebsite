package cn.jmu.marxism.commentManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/19 20:56
 * 用于保存分页的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    //每页要显示的记录条数
    private int size;
    //当前页号
    private int index;
    //总页数
    private int totalPageCount;
    //记录总条数
    private int totalCount;
    //要显示到页面的数据集合
    private List<Comment> list;
}
