package com.whalecloud.util;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域问题过滤
 *
 * @author zhaoyanac
 * @date 2019/10/18
 */
public class CORSFilter implements Filter {


    @Override
    public void init(FilterConfig var1) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));

        //防止乱码，适用于传输JSON数据

        response.setHeader("Content-Type","application/json;charset=UTF-8");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

}


