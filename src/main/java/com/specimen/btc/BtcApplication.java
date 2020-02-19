package com.specimen.btc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.specimen.btc.mapper")
@SpringBootApplication
public class BtcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtcApplication.class, args);
    }

}
