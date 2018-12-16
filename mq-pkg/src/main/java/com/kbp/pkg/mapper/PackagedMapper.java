package com.kbp.pkg.mapper;


import com.kbp.pkg.entity.Packaged;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PackagedMapper {
    int deleteByPrimaryKey(String packageId);

    int insert(Packaged record);

    int insertSelective(Packaged record);

    Packaged selectByPrimaryKey(String packageId);

    int updateByPrimaryKeySelective(Packaged record);

    int updateByPrimaryKey(Packaged record);
}