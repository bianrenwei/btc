package com.specimen.btc.domain;

import java.math.BigDecimal;

/**
 * 电表
 * @author bianrenwei
 * @date 2020-02-08 13:22
 * Copyright © SSY All Rights Reserved.
 **/
public class Meter {

    private Long id;

    /**
     * 单位id
     */
    private Long companyId;
    /**
     * 电表序号
     */
    private String serialNumber;

    /**
     * 电表度数
     */
    private BigDecimal ammeterRead;

    /**
     * 电费单价
     */
    private BigDecimal electCost;

    /**
     * 电损失
     */
    private BigDecimal electLoss;
}
