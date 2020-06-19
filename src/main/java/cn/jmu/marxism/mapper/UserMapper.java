package cn.jmu.marxism.mapper;

import cn.jmu.marxism.userManagement.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 10:52
 * User映射
 */
@Repository
public interface UserMapper {
    /**
     * 通过id查找用户
     * @param userId
     * @return User对象
     */
    @Select("select * from user where userId = #{userId}")
    User getUserById(@Param("userId") int userId);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Select("select * from user where username = #{username} and password=MD5(#{password})")
    User userLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param identification 身份，S（学生）或T（老师）
     * @return
     */
    @Select("insert into user (username, password, identification) values (#{username},MD5(#{password}),#{identification})")
    Integer register(@Param("username") String username, @Param("password") String password, @Param("identification") String identification);
}
