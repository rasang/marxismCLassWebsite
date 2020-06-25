package cn.jmu.marxism.commentManagement.controller;

import cn.jmu.marxism.commentManagement.service.CommentService;
import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.common.model.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/19 23:00
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 获取评论api，根据课件名获取评论
     * @param filename 课件名
     * @param index 当前页面
     * @param size 每页的记录数
     * @return 响应体，状态码成功为200，课件无评论为409，当前页无评论为406
     */
    @RequestMapping(value = "/comment",method = RequestMethod.GET)
    @RequireToken
    public ResponseBody getComment(@RequestParam("filename") String filename,@RequestParam("index") int index,@RequestParam("size") int size){
        return commentService.getComment(filename,index,size);
    }

    /**
     * 新增评论api
     * @param filename 评论的课件名
     * @param content 评论的内容
     * @return 响应体，状态码成功为200，失败为403
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @RequireToken
    public ResponseBody addComment(@RequestParam("filename") String filename,@RequestParam("content") String content, HttpServletRequest request){
        String token=request.getHeader("token");
        return commentService.addComment(filename,content,token);
    }

    /**
     * 删除评论api，只能删除自己的评论，只有身份为教师才可以删除他人的评论
     * @param filename 要删除的评论的课件名
     * @param id 要删除的评论的id
     * @return 响应体，状态码成功为200，失败为403，权限不足为409
     */
    @RequestMapping(value = "/comment",method = RequestMethod.DELETE)
    @RequireToken
    public ResponseBody deleteComment(@RequestParam("filename") String filename,@RequestParam("id") String id, HttpServletRequest request){
       String token=request.getHeader("token");
       return commentService.deleteComment(filename,id,token);
    }
}
