package cn.jmu.marxism.userManagement.service;

import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.common.service.Utils;
import cn.jmu.marxism.mapper.UserMapper;
import cn.jmu.marxism.userManagement.model.User;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 10:52
 * 和User有关的业务方法
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 通过id获得User对象
     * @param userId user的id
     * @return 一个User对象
     */
    public User getUserById(int userId){
        return userMapper.getUserById(userId);
    }

    /**
     * 生成一个token
     * @param user user对象
     * @return Stirng类型的token
     */
    public static String generateToken(User user) {
        long expiresTime = System.currentTimeMillis() + 7200000;
        String token = JWT.create().withAudience(user.getUserId()).withClaim("expiresTime",expiresTime)
                .withExpiresAt(new Date(expiresTime))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 登录成功返回token，用户名和身份的ResponseBody，不成功则null和对应信息
     */
    public ResponseBody userLogin(String username, String password){
        username = username.replaceAll(" ","");
        password = password.replaceAll(" ","");
        String code = null;
        String msg = null;
        JSONObject resultJson =  null;
        if(!Utils.isLegalUsername(username)){
            code = "409";
            msg = "此用户名不合法";
        }
        else{
            User user = userMapper.userLogin(username, password);
            if(user == null){
                code = "401";
                msg = "用户名或密码错误";
            }
            else{
                code = "200";
                msg = "登录成功";
                resultJson = new JSONObject();
                resultJson.put("token",this.generateToken(user));
                resultJson.put("username",user.getUsername());
                resultJson.put("identification", user.getIdentification());
            }
        }
        return new ResponseBody(code, msg, resultJson);
    }

    /**
     * 学生注册
     * @param username 用户名
     * @param password 密码
     * @return null为成功，其他则失败
     */
    public ResponseBody stuRegister(String username, String password){
        String code = null;
        String msg = null;
        username = username.replaceAll(" ","");
        password = password.replaceAll(" ","");
        if(!Utils.isLegalUsername(username)){
            code = "409";
            msg = "用户名格式错误";
        }
        try{
            Integer result = userMapper.register(username,password,"S");
            code = "200";
            msg = "注册成功";
        } catch(Exception e){
            code = "500";
            msg = "注册失败";
        }
        return new ResponseBody(code, msg, null);
    }
}
