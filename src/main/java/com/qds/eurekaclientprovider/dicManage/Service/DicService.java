package com.qds.eurekaclientprovider.dicManage.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DicService {

    List<Map> getDicInfo(String groupCode, String dicCode);

    List<Map> getAllDicInfo();

    List<String> getAllGroupCode();

    void updateDicInfo(HashMap requestMap);
}
