package com.specimen.btc.web.config.authconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bianrenwei
 * @date 2020-02-18 17:47 Copyright © SSY All Rights Reserved.
 */
@Slf4j
@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  @Autowired private ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    log.info("登录成功");
    Cookie cookie = new Cookie("vue_admin_template_token", "admin-token");
    cookie.setPath("/vue-admin-template");
    response.addCookie(cookie);
    response.setStatus(200);
    response.setContentType("application/json;charset=UTF-8");
    Object json = JSON.parse("{\"code\":20000,\"data\":{\"token\":\"admin-token\"}}");
    response.getWriter().append(json.toString());
  }
}
