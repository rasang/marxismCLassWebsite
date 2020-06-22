package cn.jmu.marxism.userInfoManagement.service;

import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.mapper.UserInfoMapper;
import cn.jmu.marxism.userInfoManagement.model.UserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/22 15:46
 */
@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 通过用户ID获得UserInfo对象
     * @param request
     * @return
     */
    public ResponseBody getUserInfoByUserId(HttpServletRequest request){
        String code;
        String msg;
        String userId = null;
        String token = request.getHeader("token");
        Object data = null;
        try{
            userId = JWT.decode(token).getAudience().get(0);
            UserInfo userInfo = userInfoMapper.getUserInfoByUserId(userId);
            if(userInfo == null){
                code = "404";
                msg = "没有信息";
            }
            else{
                code = "200";
                msg = "获取成功";
                data = userInfo;
            }
        } catch (JWTDecodeException e){
            msg = "身份信息异常";
            code = "401";
        }
        return new ResponseBody(code, msg, data);
    }

    /**
     * 传递参数对数据库userInfo进行修改
     * @param clazz
     * @param school
     * @param realName
     * @param request
     * @return
     */
    public ResponseBody updateUserInfo(String clazz, String school, String realName, HttpServletRequest request){
        String token = request.getHeader("token");
        String userId = null;
        String msg;
        String code;
        int result = -1;
        try{
            userId = JWT.decode(token).getAudience().get(0);
            result = userInfoMapper.updateUserInfo(clazz, school, realName,userId);
            if(result == 1){
                msg = "修改成功";
                code = "200";
            }
            else{
                msg = "修改失败";
                code = "500";
            }
        } catch (JWTDecodeException e){
            msg = "修改失败";
            code = "401";
        }
        return new ResponseBody(code, msg, null);
    }
}
