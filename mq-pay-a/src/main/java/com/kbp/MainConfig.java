package com.kbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.kbp.pkg.mapper")
@ComponentScan(basePackages = {"com.kbp.paya.*", "com.kbp.paya.config.*"})
public class MainConfig {

}
