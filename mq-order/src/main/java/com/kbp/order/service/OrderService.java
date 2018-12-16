package com.kbp.order.service;

/**
 * Created by kbp1234 on 2018/12/16.
 */
public interface OrderService {

    boolean createOrder(String cityId, String platformId, String userId, String supplierId, String goodsId);

    void sendOrderlyMessage4Pkg(String userId, String orderId);
}
