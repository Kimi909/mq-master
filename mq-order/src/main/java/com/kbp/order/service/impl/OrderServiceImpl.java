package com.kbp.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kbp.order.constants.OrderStatus;
import com.kbp.order.entity.Order;
import com.kbp.order.mapper.OrderMapper;
import com.kbp.order.service.OrderService;
import com.kbp.store.service.api.StoreServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by kbp1234 on 2018/12/16.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.bfxy.store.service.StoreServiceApi",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private StoreServiceApi storeServiceApi;

    @Override
    public boolean createOrder(String cityId, String platformId, String userId, String supplierId, String goodsId) {
        Boolean flag =false;


        try {
            Order order = new Order();
            order.setOrderId(UUID.randomUUID().toString().substring(0, 32));
            order.setOrderType("1");
            order.setCityId(cityId);
            order.setPlatformId(platformId);
            order.setUserId(userId);
            order.setSupplierId(supplierId);
            order.setGoodsId(goodsId);
            order.setOrderStatus(OrderStatus.ORDER_CREATED.getValue());
            order.setRemark("");

            Date currentTime = new Date();
            order.setCreateBy("admin");
            order.setCreateTime(currentTime);
            order.setUpdateBy("admin");
            order.setUpdateTime(currentTime);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }

    @Override
    public void sendOrderlyMessage4Pkg(String userId, String orderId) {

    }
}
