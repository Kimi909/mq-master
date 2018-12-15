package com.kbp.order.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kbp1234 on 2018/12/15.
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        System.out.println("---------------");
        return "index";
    }
}
