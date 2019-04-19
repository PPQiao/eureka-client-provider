package com.qds.eurekaclientprovider.dicManage.controller;

import com.qds.eurekaclientprovider.dicManage.Service.DicService;
import com.qds.eurekaclientprovider.dicManage.bean.DicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    管理字典信息
*/
@Controller
@RequestMapping("/Dic")
public class DicController {

    @Autowired
    DicService dicService;

    //***************查询服务****************//
    /*
    * 根据组code和字段code 查询具体某个code的codeName
    *
    */
    @RequestMapping("/getDicInfo")
    @ResponseBody
    public String getDicInfo(@RequestParam String groupCode, @RequestParam String dicCode){
//        System.out.println("groupCode=" + groupCode);
//        System.out.println("dicCode=" + dicCode);
        HashMap resultMap = new HashMap();
        List<Map> resultList = new ArrayList<Map>();
        if(groupCode != null && !groupCode.isEmpty() && dicCode != null && !dicCode.isEmpty()){

            try {
                resultList = dicService.getDicInfo(groupCode,dicCode);
                String dicName = resultList.get(0).get("dicName").toString();
                return dicName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    /*
    *     给一组groupCode查这些groupCode对应的字典信息
    *     应用场景: 页面上的下拉菜单
    *     @Param : String groupCode; 若有多个则用","拼接
    */
    @RequestMapping("/getDicInfoByGroupCodes")
    @ResponseBody
    public Map getDicInfoByGroupCodes(@RequestParam String groupCodes){
        //最终返回值
        HashMap resultMap = new HashMap();
        if(groupCodes != null && !groupCodes.isEmpty()){
            try {
                //分解参数
                String[] groupCodeArr = groupCodes.split(",");
                HashMap dicMap = new HashMap();
                for (String groupCode:groupCodeArr) {
                    List<Map> dicList = new ArrayList<Map>();
                    try {
                        //根据groupCode查询该groupCode下所有dicInfo
                        dicList = dicService.getDicInfo(groupCode, "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dicMap.put(groupCode,dicList);
                }
                resultMap.put("resultStatus","success");
                resultMap.put("resultData",dicMap);
                resultMap.put("resultInfo","访问成功");
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("resultStatus","error");
                resultMap.put("resultInfo","后台错误:" + e.getMessage() );
            }

        }else{
            resultMap.put("resultStatus","groupCodes不能为null或空字符串");
        }
        return resultMap;
    }

    /*
    *   可视化页面的全查询
    */
    @RequestMapping("/getAllDicInfo")
    @ResponseBody
    public Map getAllDicInfo(){
        HashMap resultMap = new HashMap();
        HashMap dicMap = new HashMap();
        try {
            List<String> groupCodeList = dicService.getAllGroupCode();
            if(groupCodeList != null && groupCodeList.size() > 0)
            for (String groupCode:groupCodeList) {
                List<Map> dicInfo = dicService.getDicInfo(groupCode, "");
                dicMap.put(groupCode,dicInfo);
            }
            resultMap.put("resultStatus","success");
            resultMap.put("resultData",dicMap);
            resultMap.put("resultInfo","访问成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultStatus","error");
            resultMap.put("resultInfo","后台错误:" + e.getMessage() );
        }
        return resultMap;
    }

    //***************插入服务****************//

    /*
    *   插入或修改(默认删掉所有的然后按照页面上的数据重新插入)
    */
    @RequestMapping("/updateDicInfo")
    @ResponseBody
    @Transactional
    public Map updateDicInfo(@RequestBody HashMap requestMap){
        HashMap resultMap = new HashMap();
        if(requestMap != null && !requestMap.isEmpty()){
            //判断参数是否正确
            String groupCode = null;
            try {
                groupCode = requestMap.get("groupCode").toString();
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("resultStatus","error");
                resultMap.put("resultInfo","参数有误:groupCode不能为null");
            }
            if("".equals(groupCode)){
                resultMap.put("resultStatus","error");
                resultMap.put("resultInfo","参数有误:groupCode不能为空字符串");
            }

            if(requestMap.get("dicInfoList") == null || requestMap.get("dicInfoList") == ""){
                resultMap.put("resultStatus","error");
                resultMap.put("resultInfo","参数有误:dicInfoList不能为空");
            }else{
                //groupCode参数正确后开始执行更新数据的操作
                dicService.updateDicInfo(requestMap);
                resultMap.put("resultStatus","success");
                resultMap.put("resultInfo","修改成功");
            }
        }
        return resultMap;
    }
}
