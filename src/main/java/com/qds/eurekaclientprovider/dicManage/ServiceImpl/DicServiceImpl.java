package com.qds.eurekaclientprovider.dicManage.ServiceImpl;

import com.qds.eurekaclientprovider.dicManage.dao.DicDaoMapper;
import com.qds.eurekaclientprovider.dicManage.Service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {

    @Autowired
    DicDaoMapper dicDaoMapper;

    @Override
    public List<Map> getDicInfo(String groupCode, String dicCode) {
        List<Map> list= dicDaoMapper.getDicInfo(groupCode,dicCode);
        return list;
    }

    @Override
    public List<Map> getAllDicInfo() {
        List<Map> list = dicDaoMapper.getAllDicInfo();
        return list;
    }

    @Override
    public List<String> getAllGroupCode() {
        List<String> list = dicDaoMapper.getAllGroupCode();
        return list;
    }

    @Override
    public void updateDicInfo(HashMap requestMap) {
        String groupCode = requestMap.get("groupCode").toString();
        List dicInfoList = (List) requestMap.get("dicInfoList");
        //如果list是空集合,说明用户把该group下的数据删除完了,执行删除操作即可
        dicDaoMapper.deleteByGroupCode(groupCode);
        //如果list不是空集合,则需要重新把list集合里的数据插入到数据库
        if(dicInfoList.size() > 0){
            System.out.println("groupCode=" + groupCode);
            System.out.println("dicInfoList=" + dicInfoList);
            dicDaoMapper.insertDicInfo(dicInfoList);
        }
    }
}
