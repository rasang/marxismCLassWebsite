package cn.jmu.marxism.common.interceptor;

import cn.jmu.marxism.common.annotation.PassToken;
import cn.jmu.marxism.common.annotation.RequireToken;
import cn.jmu.marxism.common.model.ResponseBody;
import cn.jmu.marxism.userManagement.model.User;
import cn.jmu.marxism.userManagement.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

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
     * 重写preHandler方法，在访问之前对身份进行验证，如果认证通过，用户的身份信息将存储在request中，
     * 用户身份可以通过request.getAttribute("identification")获得
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
                    response.setStatus(200);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    ResponseBody responseBody = new ResponseBody("401", "未登录",null);
                    response.getWriter().print(JSONObject.toJSONString(responseBody));
                    return false;
                }
                /* 检测token中UserId */
                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e){
                    response.setStatus(200);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    ResponseBody responseBody = new ResponseBody("401", "未登录",null);
                    response.getWriter().print(JSONObject.toJSONString(responseBody));
                    return false;
                }
                /* 检测用户是否存在 */
                User user = userService.getUserById(Integer.parseInt(userId));
                if(user == null){
                    response.setStatus(200);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    ResponseBody responseBody = new ResponseBody("401", "未登录",null);
                    response.getWriter().print(JSONObject.toJSONString(responseBody));
                    return false;
                }
                /* 检查token合法性 */
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try{
                    jwtVerifier.verify(token);
                    request.setAttribute("identification",user.getIdentification());
                    long expiresTime = JWT.decode(token).getClaim("expiresTime").asLong();
                    long test = System.currentTimeMillis();
                    if((expiresTime - System.currentTimeMillis() > 1800000)){
                        response.setHeader("refreshToken",UserService.generateToken(user));
                    }
                } catch (JWTVerificationException e){
                    response.setStatus(200);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    ResponseBody responseBody = new ResponseBody("401", "未登录",null);
                    response.getWriter().print(JSONObject.toJSONString(responseBody));
                    return false;
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
