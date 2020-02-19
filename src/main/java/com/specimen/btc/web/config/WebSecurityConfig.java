package com.specimen.btc.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author bianrenwei
 * @date 2020-02-08 11:04
 * Copyright © SSY All Rights Reserved.
 * //开启security注解
 **/
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        /*http
                .authorizeRequests()
                .antMatchers("/", "/home","login1").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/

        /*http.cors().and().formLogin()
                .failureHandler(myAuthenctiationFailureHandler) // 自定义登录失败处理
                .successHandler(myAuthenctiationSuccessHandler) // 自定义登录成功处理
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().maximumSessions(1)
                .and()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/meureka/login1")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/

        http.cors().and().csrf().disable().formLogin()
                .failureHandler(myAuthenctiationFailureHandler) // 自定义登录失败处理
                .successHandler(myAuthenctiationSuccessHandler) // 自定义登录成功处理
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home","login","/meureka/login","*").permitAll()//定义哪些url需要保护，哪些url不需要保护
                .anyRequest().authenticated()
                .and()
                .sessionManagement().maximumSessions(1)
                .and()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .formLogin()
                .loginPage("/login.html")  //定义当需要用户登录时候，转到的登录页面
                .loginProcessingUrl("/meureka/login")  // 自定义的登录接口
                .permitAll()
                .defaultSuccessUrl("/index.html").permitAll()
                .and()
                .logout()
                .permitAll()
                // 自动登录
                .and().rememberMe();
        http.csrf().disable();
        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);

    }

/*    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("111111")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("123456")
                .roles("USER");
        //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
    }
}
