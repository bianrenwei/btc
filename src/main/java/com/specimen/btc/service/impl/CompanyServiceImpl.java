package com.specimen.btc.service.impl;

import com.specimen.btc.domain.Company;
import com.specimen.btc.mapper.CompanyMapper;
import com.specimen.btc.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bianrenwei
 * @date 2020-02-08 11:48
 * Copyright © SSY All Rights Reserved.
 **/
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    CompanyMapper companyMapper;

    @Override
    public List<Company> getAll() {
        return companyMapper.getAll();
    }
}
