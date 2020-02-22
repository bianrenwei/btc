package com.specimen.btc.web.config.authconfig;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.specimen.btc.web.config.MyAuthenctiationFailureHandler;
import com.specimen.btc.web.config.MyAuthenctiationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author bianrenwei
 * @date 2020-02-08 11:04 Copyright © SSY All Rights Reserved. //开启security注解
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  PasswordEncoder passwordEncoder() {

    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    // 这里只做如何使用passwordEncoder与校验保持一致示例 密码输出
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication().withUser("zhangsan").password("123").roles("user");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {}

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .and()
        .csrf().disable();

    http.addFilterAt(
        customAuthenticationFilter(),
        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

    //    http.cors()
    //        .and()
    // 解决中文乱码问题
    /*CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    http.addFilterBefore(filter, CsrfFilter.class);*/
  }

  @Bean
  CustomAuthenticationFilter customAuthenticationFilter() throws Exception {

    CustomAuthenticationFilter filter = new CustomAuthenticationFilter();

    filter.setAuthenticationSuccessHandler(
        new AuthenticationSuccessHandler() {

          @Override
          public void onAuthenticationSuccess(
              HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
              throws IOException, ServletException {
            resp.setContentType("application/json;charset=utf-8");
            Cookie cookie = new Cookie("vue_admin_template_token", "admin-token");
            resp.addCookie(cookie);
            PrintWriter out = resp.getWriter();

            RespBean respBean = RespBean.ok("登录成功!");
            Object json = JSON.parse("{\"code\":20000,\"data\":{\"token\":\"admin-token\"}}");

            out.write(new ObjectMapper().writeValueAsString(json));

            out.flush();

            out.close();
          }
        });

    filter.setAuthenticationFailureHandler(
        new AuthenticationFailureHandler() {

          @Override
          public void onAuthenticationFailure(
              HttpServletRequest req, HttpServletResponse resp, AuthenticationException e)
              throws IOException, ServletException {

            resp.setContentType("application/json;charset=utf-8");

            PrintWriter out = resp.getWriter();

            RespBean respBean = RespBean.error("登录失败!");
            out.write(
                new ObjectMapper()
                    .writeValueAsString(
                        JSON.parse(
                            "{\"code\":60204,\"message\":\"Account and password are incorrect.\"}")));

            out.flush();

            out.close();
          }
        });

    filter.setAuthenticationManager(authenticationManagerBean());

    return filter;
  }

  public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
    Cookie[] cookieList = request.getCookies();
    if (cookieList != null && cookieName != null) {
      for(int i = 0; i < cookieList.length; ++i) {
        try {
          if (cookieList[i].getName().equals(cookieName)) {
            return cookieList[i].getValue();
          }
        } catch (Exception var6) {
          log.error("getCookieValue(HttpServletRequest, String, String)", var6);
        }
      }

      return defaultValue;
    } else {
      return defaultValue;
    }
  }

}
