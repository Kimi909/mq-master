package com.kbp.paya.mapper;

import com.kbp.paya.entity.CustomerAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
@Mapper
public interface CustomerAccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(CustomerAccount record);

    int insertSelective(CustomerAccount record);

    CustomerAccount selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(CustomerAccount record);

    int updateByPrimaryKey(CustomerAccount record);

	int updateBalance(@Param("accountId") String accountId, @Param("newBalance") BigDecimal newBalance, @Param("version") int currentVersion, @Param("updateTime") Date currentTime);

}