package cn.jmu.marxism;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 17:50
 * 注解不需要登录认证的方法
 */
@MapperScan("cn.jmu.marxism.mapper")
@ServletComponentScan("cn.jmu.marxism.common.xssFilter")
@SpringBootApplication
public class MarxismApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarxismApplication.class, args);
    }

}
