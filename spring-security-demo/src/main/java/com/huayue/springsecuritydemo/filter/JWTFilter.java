package com.huayue.springsecuritydemo.filter;

import com.alibaba.fastjson.JSON;
import com.huayue.springsecuritydemo.config.SysUserDetailsService;
import com.huayue.springsecuritydemo.global.Result;
import com.huayue.springsecuritydemo.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
@Slf4j
@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private SysUserDetailsService sysUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String authToken = authHeader.substring("Bearer ".length());
            try {
                String newToken = JwtUtil.refreshToken(authToken);
                if (newToken != null) {
                    httpServletResponse.setHeader("authentication", newToken);
                }
            } catch (JwtException e) {
                log.error(e.toString());
                httpServletResponse.getWriter().write(JSON.toJSONString(Result.failure("无效的token，请重新登陆后操作")));
                return;
            }
            String username;
            try {
                username = JwtUtil.parseToken(authToken);
            } catch (JwtException e) {
                log.error(e.toString());
                httpServletResponse.getWriter().write(JSON.toJSONString(Result.failure("无效的token，请重新登陆后操作")));
                return;
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = sysUserDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
