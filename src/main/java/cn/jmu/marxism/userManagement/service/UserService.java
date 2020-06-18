package cn.jmu.marxism.userManagement.service;

import cn.jmu.marxism.common.service.Utils;
import cn.jmu.marxism.userManagement.mapper.UserMapper;
import cn.jmu.marxism.userManagement.model.User;
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
     * @return 登录成功返回token，不成功则null
     */
    public String userLogin(String username, String password){
        username = username.replaceAll(" ","");
        password = password.replaceAll(" ","");
        if(!Utils.isLegalUsername(username)){
            return "409";
        }
        User user = userMapper.userLogin(username, password);
        if(user == null){
            return null;
        }
        else{
            return this.generateToken(user);
        }
    }

    /**
     * 学生注册
     * @param username 用户名
     * @param password 密码
     * @return null为成功，其他则失败
     */
    public Integer stuRegister(String username, String password){
        username = username.replaceAll(" ","");
        password = password.replaceAll(" ","");
        if(!Utils.isLegalUsername(username)){
            return -1;
        }
        return userMapper.register(username,password,"S");
    }
}
