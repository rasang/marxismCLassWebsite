package cn.jmu.marxism.userManagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 10:52
 * User的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String userId;
    String username;
    String password;
    String identification;
}
