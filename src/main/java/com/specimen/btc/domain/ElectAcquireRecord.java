package com.specimen.btc.domain;

import java.util.Date;

/**
 * 电表采集记录
 * @author bianrenwei
 * @date 2020-02-08 13:58
 * Copyright © SSY All Rights Reserved.
 **/
public class ElectAcquireRecord {

    private Long id;



    /**
     * 采集开始时间
     */
    private Date acquireStartTime;

    /**
     * 采集结束时间
     */
    private Date acquireEndTime;

    /**
     * 统计时间
     */
    private Long dataTime;

    private Date createTime;

}
