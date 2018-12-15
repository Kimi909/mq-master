package com.kbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.kbp.order.mapper")
@ComponentScan(basePackages = {"com.kbp.order.*", "com.kbp.order.config.*"})
public class MainConfig {

}
