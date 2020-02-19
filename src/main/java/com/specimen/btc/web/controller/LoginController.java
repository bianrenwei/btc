package com.specimen.btc.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bianrenwei
 * @date 2020-02-08 10:21
 * Copyright © SSY All Rights Reserved.
 **/
@RestController
//@RequestMapping("/login")
public class LoginController {

    @GetMapping("login1")
    public String login(){
        return "login success";
    }

    @GetMapping("error")
    public String error(){
        return "login error";
    }

}
