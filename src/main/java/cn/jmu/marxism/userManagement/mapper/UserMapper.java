package cn.jmu.marxism.userManagement.mapper;

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

    @Select("select * from user where username = #{username} and password=PASSWORD(#{password})")
    User userLogin(@Param("username") String username, @Param("password") String password);

    @Select("insert into user (username, password, identification) values (#{username},PASSWORD(#{password}),'#{identification})")
    int register(@Param("username") String username, @Param("password") String password, @Param("identification") String identification);
}
