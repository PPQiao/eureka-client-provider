package com.qds.eurekaclientprovider.dicManage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DicDaoMapper {
    List<Map> getDicInfo(@Param("groupCode") String groupCode,@Param("dicCode") String dicCode);

    List<Map> getAllDicInfo();

    List<String> getAllGroupCode();

    void deleteByGroupCode(String groupCode);

    void insertDicInfo(@Param("dicInfoList") List dicInfoList);
}
