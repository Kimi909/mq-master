package com.kbp.store.service.api;

import java.util.Date;

/**
 * Created by kbp1234 on 2018/12/16.
 */
public interface StoreServiceApi {

    public int selectVersion(String supplierId, String goodsId);

    public int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy,
                                         Date updateTime);

    public int selectStoreCount(String supplierId, String goodsId);

}
