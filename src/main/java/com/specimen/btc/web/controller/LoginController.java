package com.specimen.btc.web.controller;

import com.specimen.btc.web.config.authconfig.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bianrenwei
 * @date 2020-02-08 10:21 Copyright © SSY All Rights Reserved.
 */
@RestController
// @RequestMapping("/login")
public class LoginController {
  @GetMapping("login1")
  public String login() {
    return "login success";
  }

  @GetMapping("error")
  public String error() {
    return "login error";
  }

  @GetMapping("/login")
  public RespBean login1() {

    return RespBean.error("尚未登录，请登录");
  }

  @GetMapping("/user/info")
  public String hello(@RequestParam String token) {
    return "{\"code\":20000,\"data\":{\"roles\":[\"admin\"],\"introduction\":\"I am a super administrator\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"name\":\"Super Admin\"}}";
  }
}
