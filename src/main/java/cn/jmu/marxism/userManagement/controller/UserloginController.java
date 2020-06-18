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
     * @return 业务层的响应体响应体
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBody login(@RequestParam("username") String username, @RequestParam("password") String password){
        ResponseBody responseBody = userService.userLogin(username, password);
        return  responseBody;
    }

    /**
     * 用户注册API
     * @param username 用户名
     * @param password 用户密码
     * @return 响应体，成功状态码为200，格式错误为409，其他不明失败原因为401
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBody register(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.stuRegister(username, password);
    }
}
