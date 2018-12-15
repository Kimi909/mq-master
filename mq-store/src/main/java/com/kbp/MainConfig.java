package com.kbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.kbp.store.mapper")
@ComponentScan(basePackages = {"com.kbp.store.*", "com.kbp.store.config.*"})
public class MainConfig {

}
