package cn.jmu.marxism.mapper;

import cn.jmu.marxism.commentManagement.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/19 21:07
 */
@Repository
public interface CommentMapper {
    /**
     * 根据课件名获取评论
     * @param filename 评论的课件名
     * @return 该课件的所有评论
     */
    @Select("select * from comment where filename=#{filename} order by time desc")
    public List<Comment> getComment(@Param("filename") String filename);

    /**
     * 增加评论
     * @param comment 增加的评论
     * @return 增加的结果
     */
    @Insert("insert into comment(filename,username,content,time) values(#{filename},#{username},#{content},#{time})")
    public int addComment(Comment comment);

    /**
     * 删除评论
     * @param comment 要删除的评论的信息
     * @return 删除的结果
     */
    @Delete("delete from comment where filename=#{filename} and username=#{username} and time=#{time}")
    public int deleteComment(Comment comment);
}
