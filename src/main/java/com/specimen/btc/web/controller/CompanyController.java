package com.specimen.btc.web.controller;

import com.specimen.btc.domain.Company;
import com.specimen.btc.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bianrenwei
 * @date 2020-02-08 12:06
 * Copyright © SSY All Rights Reserved.
 **/
@RequestMapping("/company")
@RestController
public class CompanyController {
    @Resource
    CompanyService companyService;

    @GetMapping("list")
    public List<Company> list() {
        return companyService.getAll();
    }
}
