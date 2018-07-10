package com.blog.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author shibo
 */
public class CrossDomainFilter implements Filter {
    private final List<String> allowedOrigins = Arrays.asList("http://localhost:8089","http://localhost:8088");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Accept, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
