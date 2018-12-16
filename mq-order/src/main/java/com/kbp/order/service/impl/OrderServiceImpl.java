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
            interfaceName = "com.kbp.store.service.api.StoreServiceApi",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private StoreServiceApi storeServiceApi;

    @Override
    public boolean createOrder(String cityId, String platformId, String userId, String supplierId, String goodsId) {
        Boolean flag =true;


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
            System.out.println("supplierId: " +supplierId + ",goodsId:  " + goodsId);
            int currentVersion = storeServiceApi.selectVersion(supplierId, goodsId);
            int updateRetCount = storeServiceApi.updateStoreCountByVersion(currentVersion, supplierId, goodsId, "admin", currentTime);

            if(updateRetCount == 1){
                // DOTO:	如果出现SQL异常 入库失败, 那么要对 库存的数量 和版本号进行回滚操作
                orderMapper.insertSelective(order);

                //	没有更新成功 1 高并发时乐观锁生效   2 库存不足
            }else if(updateRetCount == 0){
                flag =false;
                int currentStoreCount = storeServiceApi.selectStoreCount(supplierId, goodsId);
                if(currentStoreCount == 0) {
                    //{flag:false , messageCode: 003 , message: 当前库存不足}
                    System.err.println("-----当前库存不足...");
                } else {
                    //{flag:false , messageCode: 004 , message: 乐观锁生效}
                    System.err.println("-----乐观锁生效...");
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
            // 	具体捕获的异常是什么异常
            flag = false;
        }


        return flag;
    }

    @Override
    public void sendOrderlyMessage4Pkg(String userId, String orderId) {

    }
}
