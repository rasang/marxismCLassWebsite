package cn.jmu.marxism.userManagement.controller;

import cn.jmu.marxism.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.jmu.marxism.common.model.ResponseBody;
/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 11:00
 */
@CrossOrigin(maxAge = 3600)
@RestController
public class UserloginController {
    @Autowired
    UserService userService;

    /**
     * 用户登录API
     * @param username 用户名
     * @param password 用户密码
     * @return 响应体，成功状态码为200，用户名或密码错误为401，格式错误为409
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBody login(@RequestParam("username") String username, @RequestParam("password") String password){
        String token = userService.userLogin(username, password);
        if(token == null){
            return new ResponseBody("401", "用户名或密码错误",token);
        }
        else if(token.equals("409")){
            return new ResponseBody("409","用户名格式错误",null);
        }
        else{
            return new ResponseBody("200", "登录成功", token);
        }
    }

    /**
     * 用户注册API
     * @param username 用户名
     * @param password 用户密码
     * @return 响应体，成功状态码为200，格式错误为409，其他不明失败原因为401
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping(value = "/register")
    public ResponseBody register(@RequestParam("username") String username, @RequestParam("password") String password){
        String code = null;
        String msg = null;
        try{
            Integer result = userService.stuRegister(username, password);
            code = "200";
            msg = "注册成功";
            if(result == -1){
                code = "409";
                msg = "用户名格式错误";
            }
        } catch(Exception e){
            code = "401";
            msg = "注册失败";
        }
        return new ResponseBody(code, msg, null);
    }
}
