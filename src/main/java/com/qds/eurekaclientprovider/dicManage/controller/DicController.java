package com.qds.eurekaclientprovider.dicManage.controller;

import com.qds.eurekaclientprovider.dicManage.Service.DicService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    管理字典信息
*/
@Controller
@RequestMapping("/Dic")
@Api(value = "DicController",description = "管理字典表")
public class DicController {

    @Autowired
    DicService dicService;

    //***************查询服务****************//
    /*
    * 根据组code和字段code 查询具体某个code的codeName
    *
    */
    @RequestMapping(value = "/getDicInfo",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据groupCode和dicCode获取指定的dicName")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="groupCode",dataType="String",required=true,value="组名",defaultValue="color"),// 每个参数的类型，名称，数据类型，是否校验，描述，默认值(这些在界面上有展示)
            @ApiImplicitParam(paramType="query",name="dicCode",dataType="String",required=true,value="字段代码",defaultValue="0")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"), // 响应对应编码的描述
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
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
    @ApiOperation(value = "根据组名获取改组下所有字段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="groupCodes",dataType="String",required=true,value="组名代码,如果有多个,用','拼接")
    })
    @RequestMapping(value = "/getDicInfoByGroupCodes",method = RequestMethod.POST)
    @ResponseBody
    public Map getDicInfoByGroupCodes(@RequestParam("groupCodes") String groupCodes){
//        String groupCodes = (String)requestMap.get("groupCodes");
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
    @ApiOperation(value = "表全查")
    @RequestMapping(value = "/getAllDicInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map getAllDicInfo(){
        HashMap resultMap = new HashMap();
        HashMap dicMap = new HashMap();
        try {
            List<String> groupCodeList = dicService.getAllGroupCode();
            if(groupCodeList != null && groupCodeList.size() > 0)
            for (String groupCode:groupCodeList) {
                List<Map> dicInfo = dicService.getDicInfo(groupCode, "");
                String groupCodeAndName = groupCode +"|"+ dicInfo.get(0).get("groupName");
                dicMap.put(groupCodeAndName,dicInfo);
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
    @ApiOperation(value = "更新页面数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="requestMap",dataType="json/text",required=true,value="该组名及对应组下的数据")
    })
    @RequestMapping(value = "/updateDicInfo",method = RequestMethod.POST)
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
