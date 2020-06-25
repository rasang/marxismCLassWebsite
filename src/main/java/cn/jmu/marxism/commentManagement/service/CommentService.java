package cn.jmu.marxism.commentManagement.service;

import cn.jmu.marxism.commentManagement.model.Comment;
import cn.jmu.marxism.commentManagement.model.Page;
import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.mapper.CommentMapper;
import cn.jmu.marxism.userManagement.model.User;
import cn.jmu.marxism.userManagement.service.UserService;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/19 21:31
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserService userService;

    /**
     * 根据课件名获取评论，并进行分页
     * @param filename 课件名
     * @param index 当前页数
     * @param size 每页的记录数
     * @return 包含获取结果的ResponseBody
     */
    public ResponseBody getComment(String filename,int index,int size){
        List<Comment> list=commentMapper.getComment(filename);
        if(list==null){
            return new ResponseBody("409","当前课件无评论",null);
        }
        int totalCount=list.size();
        int fromIndex=(index-1)*size;
        if(fromIndex>=totalCount){
            return new ResponseBody("406","当前页面没有评论",null);
        }
        int toIndex=index*size;
        if(toIndex>totalCount){
            toIndex=totalCount;
        }
        List<Comment> pageList=list.subList(fromIndex,toIndex);
        int totalPageCount=(totalCount+size-1)/size;
        Page page=new Page(size,index,totalPageCount,totalCount,pageList);
        return new ResponseBody("200","获取成功",page);
    }

    /**
     * 增加评论
     * @param filename 评论的课件
     * @param content 评论的内容
     * @param token 用户的token
     * @return 增加的结果
     */
    public ResponseBody addComment(String filename,String content,String token){
        Comment comment=new Comment();
        String userId=JWT.decode(token).getAudience().get(0);
        User user=userService.getUserById(Integer.parseInt(userId));
        comment.setFilename(filename);
        comment.setContent(content);
        comment.setUsername(user.getUsername());
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setTime(df.format(new Date()));
        int result=commentMapper.addComment(comment);
        if(result==1){
            return new ResponseBody("200","新增评论成功",null);
        }else {
            return new ResponseBody("403","新增评论失败",null);
        }
    }

    /**
     * 删除评论
     * @param filename 要删除的评论的课件
     * @param id 评论id
     * @param token 用户的token
     * @return 删除的结果
     */
    public ResponseBody deleteComment(String filename,String id,String token){
        String userId= JWT.decode(token).getAudience().get(0);
        User user=userService.getUserById(Integer.parseInt(userId));
        Comment comment=new Comment();
        comment.setUsername(user.getUsername());
        comment.setFilename(filename);
        comment.setId(Integer.parseInt(id));
        int result=commentMapper.deleteComment(comment);
        if(result==1){
            return new ResponseBody("200","删除成功",null);
        }else {
            return new ResponseBody("403","删除失败",null);
        }
    }
}
