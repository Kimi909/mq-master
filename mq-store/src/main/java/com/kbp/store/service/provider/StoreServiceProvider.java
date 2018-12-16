package com.kbp.store.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.kbp.store.mapper.StoreMapper;
import com.kbp.store.service.api.StoreServiceApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)

public class StoreServiceProvider implements StoreServiceApi {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public int selectVersion(String supplierId, String goodsId) {
        System.out.println("supplierId: " + supplierId + ",goodsId " + goodsId);
        int version = storeMapper.selectVersion(supplierId,goodsId);
        System.out.println(version);
        return version;
    }

    @Override
    public int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy, Date updateTime) {
        return storeMapper.updateStoreCountByVersion(version,supplierId,goodsId,updateBy,updateTime);
    }

    @Override
    public int selectStoreCount(String supplierId, String goodsId) {
        return storeMapper.selectStoreCount(supplierId,goodsId);
    }
}
