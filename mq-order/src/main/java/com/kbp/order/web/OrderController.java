package com.kbp.order.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kbp1234 on 2018/12/15.
 */
@RestController
public class OrderController {

    //超时降级，调用另一个方法
  /*  @HystrixCommand(
           commandKey = "createOrder",
           commandProperties = {
                   @HystrixProperty(name="execution.timeout.enabled",value = "true"),
                   @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
           },
           fallbackMethod = "createOrderFallbackMethod"
    )*/

    	@HystrixCommand(
				commandKey = "createOrder",
				commandProperties = {
						@HystrixProperty(name="execution.timeout.enabled", value="true"),
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000")
				},
				fallbackMethod = "createOrderFallbackMethod4Timeout"
			)
    @RequestMapping("/createOrder")
    public String createOrder(@RequestParam("cityId")String cityId,
                              @RequestParam("platformId")String platformId,
                              @RequestParam("userId")String userId,
                              @RequestParam("supplierId")String supplierId,
                              @RequestParam("goodsId")String goodsId) throws Exception {
        Thread.sleep(50000000);

        return "下单成功";
    }


    public String createOrderFallbackMethod4Timeout(@RequestParam("cityId")String cityId,
                              @RequestParam("platformId")String platformId,
                              @RequestParam("userId")String userId,
                              @RequestParam("supplierId")String supplierId,
                              @RequestParam("goodsId")String goodsId){


        System.err.println("-------超时降级策略执行------------");
        return "hysrtix timeout !";
    }
}
