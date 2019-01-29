package com.huayue.springsecuritydemo.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/23.
 */
@Component
public class ResponseFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
