package com.specimen.btc.mapper;

import com.specimen.btc.domain.Company;
import com.specimen.btc.domain.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bianrenwei
 * @date 2020-02-08 11:57
 * Copyright © SSY All Rights Reserved.
 **/
public interface CompanyMapper {

    /**
     * 功能描述：查找全部
     * @return
     */
    @Select("SELECT * FROM company")
    @Results({
            @Result(column = "create_time",property = "createTime")
    })
    List<Company> getAll();
}
