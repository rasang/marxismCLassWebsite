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

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBody login(@RequestParam("username") String username, @RequestParam("password") String password){
        String token = userService.userLogin(username, password);
        if(token == null){
            return new ResponseBody("401", "用户名或密码错误",token);
        }
        else{
            return new ResponseBody("200", "登录成功", token);
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping(value = "/register")
    public ResponseBody register(@RequestParam("username") String username, @RequestParam("password") String password){
        return null;
    }
}
