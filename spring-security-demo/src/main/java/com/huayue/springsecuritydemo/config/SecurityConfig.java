package com.huayue.springsecuritydemo.config;

import com.huayue.springsecuritydemo.filter.JWTFilter;
import com.huayue.springsecuritydemo.filter.ResponseFilter;
import com.huayue.springsecuritydemo.handler.AuthFailureHandler;
import com.huayue.springsecuritydemo.handler.AuthSuccessHandler;
import com.huayue.springsecuritydemo.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/18.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private SysUserDetailsService sysUserDetailsService;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private ResponseFilter responseFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void setSysUserDetailsService(SysUserDetailsService sysUserDetailsService){
        this.sysUserDetailsService = sysUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/home/**","/users/**").permitAll()
                .antMatchers("/index/**").hasRole("ADMIN")
                .anyRequest().hasRole("USER")
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .and()
                .formLogin()
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(responseFilter, JWTFilter.class);
    }
}
