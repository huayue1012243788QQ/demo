package com.huayue.springsecuritydemo.handler;

import com.alibaba.fastjson.JSON;
import com.huayue.springsecuritydemo.global.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.getWriter().write(JSON.toJSONString(Result.failure("Login failure!")));
    }
}
