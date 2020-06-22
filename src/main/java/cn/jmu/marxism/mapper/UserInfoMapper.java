package cn.jmu.marxism.mapper;

import cn.jmu.marxism.userInfoManagement.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/22 15:18
 */
@Repository
public interface UserInfoMapper {
    /**
     * 根据userId获得userInfo对象
     * @param userId
     * @return
     */
    @Select("select * from userInfo where userId = #{userId}")
    UserInfo getUserInfoByUserId(@Param("userId") String userId);

    /**
     * 根据Username去更改数据库的userInfo数据
     * @param clazz
     * @param school
     * @param realName
     * @param userId
     * @return
     */
    @Update("update userInfo set clazz=#{clazz}, school = #{school}, realName = #{realName} where userId=#{userId}")
    int updateUserInfo(@Param("clazz") String clazz, @Param("school") String school, @Param("realName") String realName, @Param("userId") String userId);
}
