package com.kbp.order.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kbp.store.service.api.HelloServiceApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.kbp.store.service.api",
            check = false,
            timeout = 1000,
            retries = 0
    )
	private HelloServiceApi helloService;
    
    @RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		System.out.println("------");
		return helloService.sayHello(name);
	}
}
