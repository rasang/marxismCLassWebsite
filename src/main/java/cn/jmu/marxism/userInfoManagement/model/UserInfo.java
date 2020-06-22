package cn.jmu.marxism.userInfoManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/22 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    String userId;
    String school;
    String clazz;
    String realName;
}
