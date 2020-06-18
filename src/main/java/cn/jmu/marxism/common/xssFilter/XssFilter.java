package cn.jmu.marxism.common.xssFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xingkyh
 * @version 1.0
 * @date 2020/6/18 11:44
 * 自定义过滤器拦截请求(创建过滤器)
 */
@WebFilter(filterName = "XssFilter", urlPatterns = {"/courseIntroduce/*","/teachPlan/*"})
public class XssFilter implements Filter{
    FilterConfig filterConfig = null;

    @Override
    //过滤器初始化
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    //执行过滤器操作
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    //过滤器销毁
    public void destroy() {
        this.filterConfig = null;
    }
}
