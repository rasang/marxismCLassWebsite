package cn.jmu.marxism.userManagement.controller;

import cn.jmu.marxism.userManagement.annotation.RequireToken;
import cn.jmu.marxism.userManagement.model.User;
import cn.jmu.marxism.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 10:57
 * 用来测试的
 */
@RestController
public class testController {
    @Autowired
    UserService userService;
    @RequireToken
    @RequestMapping("/test")
    public User test(){
        return userService.getUserById(1);
    }
}
