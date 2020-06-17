package cn.jmu.marxism.userManagement.interceptor;

import cn.jmu.marxism.userManagement.annotation.PassToken;
import cn.jmu.marxism.userManagement.annotation.RequireToken;
import cn.jmu.marxism.userManagement.model.User;
import cn.jmu.marxism.userManagement.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 10:52
 * 登录认证的拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    /**
     * 重写preHandler方法，在访问之前对身份进行验证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        /* 检查方法注解 */
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        /* 检查是否为PassToken */
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }
        /* 检查是否为RequiredToken */
        if(method.isAnnotationPresent(RequireToken.class)){
            RequireToken requireToken = method.getAnnotation(RequireToken.class);
            /* 检查token是否存在 */
            if(requireToken.required()){
                if(token == null){
                    throw new RuntimeException("未登录");
                }
                /* 检测token中UserId */
                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e){
                    throw new RuntimeException("401");
                }
                /* 检测用户是否存在 */
                User user = userService.getUserById(Integer.parseInt(userId));
                if(user == null){
                    throw new RuntimeException("用户不存在");
                }
                /* 检查token合法性 */
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try{
                    jwtVerifier.verify(token);
                    long expiresTime = JWT.decode(token).getClaim("expiresTime").asLong();
                    long test = System.currentTimeMillis();
                    if((expiresTime - System.currentTimeMillis() > 1800000)){
                        response.setHeader("refreshToken",UserService.generateToken(user));
                    }
                } catch (JWTVerificationException e){
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
