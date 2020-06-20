package cn.jmu.marxism.userManagement.controller;

import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
    public HashMap test(HttpServletRequest request){
        HashMap hashMap = new HashMap();
        hashMap.put("test1",request.getAttribute("identification"));
        return hashMap;
    }

}
