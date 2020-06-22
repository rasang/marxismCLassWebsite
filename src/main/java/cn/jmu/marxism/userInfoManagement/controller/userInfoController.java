package cn.jmu.marxism.userInfoManagement.controller;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/22 16:12
 */

import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.userInfoManagement.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class userInfoController {
    @Autowired
    UserInfoService userInfoService;

    /**
     * 获取用户信息api，使用get方法请求
     * @param request
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @RequireToken
    public ResponseBody getuserInfo(HttpServletRequest request){
        return userInfoService.getUserInfoByUserId(request);
    }

    /**
     * 修改用户信息api，通过post请求传递参数
     * @param request
     * @param clazz
     * @param school
     * @param realName
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @RequireToken
    public ResponseBody updateUserInfo(HttpServletRequest request, @RequestParam("clazz") String clazz,
                                       @RequestParam("school") String school, @RequestParam("realName") String realName){
        return userInfoService.updateUserInfo(clazz, school, realName, request);
    }
}
