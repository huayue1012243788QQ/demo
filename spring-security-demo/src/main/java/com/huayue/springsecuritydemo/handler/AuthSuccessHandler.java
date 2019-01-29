package com.huayue.springsecuritydemo.handler;

import com.alibaba.fastjson.JSON;
import com.huayue.springsecuritydemo.global.Result;
import com.huayue.springsecuritydemo.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
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
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        response.getWriter().write(JSON.toJSONString(Result.success(JwtUtil.generateToken(userDetails.getUsername(), 1209600))));
    }
}
