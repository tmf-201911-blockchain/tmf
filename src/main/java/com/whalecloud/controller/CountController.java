package com.whalecloud.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whalecloud.domain.MyQuotation;
import com.whalecloud.domain.Progress;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 资源统计
 *  定期从链上获取数据
 * @date 2019/10/14
 */
@Controller
@RequestMapping("/count/")
public class CountController {

    Gson gson = new GsonBuilder().create();
    @Autowired
    CountService countService;
    /**
     * 查询总的基站数量
     * @return
     */
    @RequestMapping(value = "getTotalCount",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getTotalCount(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int totalCount = countService.getTotalCount(resourceType);
        String json = gson.toJson(totalCount);
        return json;

    }

    /**
     * 查询联通的基站数量
     * @return
     */
    @RequestMapping(value = "getUnicomCount",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUnicomCount(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int unicomCount = countService.getUnicomCount(resourceType);
        String json = gson.toJson(unicomCount);
        return json;
    }

    /**
     * 查询电信的基站数量
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTelecomCount",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getTelecomCount(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int telecomCount = countService.getTelecomCount(resourceType);
        String json = gson.toJson(telecomCount);
        return json;
    }

    /**
     * 根据资源类型查询总数/联通总数/电信总数
     * @param resourceType
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCountByResourceType",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getCountByResourceType(String resourceType,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Integer> list = countService.getCountByResourceType(resourceType);
        String s = gson.toJson(list);
        return s;
    }

    /**
     * 查询联通和电信的基站数量所占百分比
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPercent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getPercent(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //联通
        int unicomCount = countService.getUnicomCount(resourceType);

        //电信
        int telecomCount = countService.getTelecomCount(resourceType);

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        map1.put("value",unicomCount);
        map1.put("name","China Unicom");
        map2.put("value",telecomCount);
        map2.put("name","China Telecom");

        list.add(map1);
        list.add(map2);
        String json = gson.toJson(list);
        /*
        [
              {value:60, name:'中国联通'},
              {value:80, name:'中国电信'},
            ]
        responseMapData1.put("name", "China");
		responseMapData1.put("value", cnPatentCount);
		responseListData.add(responseMapData1);

            */

        return json;
    }

    //总的使用中,建设中,规划中,维护中 station status
    @ResponseBody
    @RequestMapping(value = "getStationStatus",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getStationStatus(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        //总的使用中
        int totalUsing = countService.getTelecomUsing(resourceType) + countService.getUnicomUsing(resourceType);
        //总的建设中
        int totalBuilding = countService.getTelecomBuilding(resourceType) + countService.getUnicomBuilding(resourceType);
        //总的规划中
        int totalPlaning = countService.getTelecomPlaning(resourceType) + countService.getUnicomPlaning(resourceType);
        //总的维护中
        int totalMaintenance = countService.getTelecomMaintenance(resourceType) + countService.getUnicomMaintenance(resourceType);

        List<Map<String,Integer>> list = new ArrayList<>();
        Map<String,Integer> stationStatusMap = new HashMap<>();

        stationStatusMap.put("Running",totalUsing);
        stationStatusMap.put("Constructing",totalBuilding);
        stationStatusMap.put("Planning",totalPlaning);
        stationStatusMap.put("Maintaining",totalMaintenance);

        list.add(stationStatusMap);

        String json = gson.toJson(list);
        return json;
    }


    //总的使用中
    @ResponseBody
    @RequestMapping(value = "getTotalUsing",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getTotalUsing(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int totalUsing = countService.getTelecomUsing(resourceType) + countService.getUnicomUsing(resourceType);
        String json = gson.toJson(totalUsing);
        return json;
    }

    //联通使用中,建设中,规划中,维护中占联通总的百分比
    @ResponseBody
    @RequestMapping(value = "getUnicomPercent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getUnicomPercent(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //使用中
        int unicomUsing = countService.getUnicomUsing(resourceType);
        //建设中
        int unicomBuilding = countService.getUnicomBuilding(resourceType);
        //规划中
        int unicomPlaning = countService.getUnicomPlaning(resourceType);
        //维护中
        int unicomMaintenance = countService.getUnicomMaintenance(resourceType);

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        Map<String, Object> map4 = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        map1.put("value",unicomUsing);
        map1.put("name","Running");
        map2.put("value",unicomBuilding);
        map2.put("name","Constructing");
        map3.put("value",unicomPlaning);
        map3.put("name","Planning");
        map4.put("value", unicomMaintenance);
        map4.put("name", "Maintaining");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        String json = gson.toJson(list);
        return json;
    }

    //电信使用中,在建中,规划中,维护中占电信总的百分比
    @ResponseBody
    @RequestMapping(value = "getTelecomPercent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getTelecomPercent(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //使用中
        int telecomUsing = countService.getTelecomUsing(resourceType);
        //在建中
        int telecomBuilding = countService.getTelecomBuilding(resourceType);
        //规划中
        int telecomPlaning = countService.getTelecomPlaning(resourceType);
        //维护中
        int telecomMaintenance = countService.getTelecomMaintenance(resourceType);

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        Map<String, Object> map4 = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        map1.put("value",telecomUsing);
        map1.put("name","Running");
        map2.put("value",telecomBuilding);
        map2.put("name","Constructing");
        map3.put("value",telecomPlaning);
        map3.put("name","Planning");
        map4.put("value", telecomMaintenance);
        map4.put("name", "Maintaining");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        String json = gson.toJson(list);
        return json;

    }


    //总的建设中
    @ResponseBody
    @RequestMapping(value = "getTotalBuilding",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getTotalBuilding(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int totalBuilding = countService.getTelecomBuilding(resourceType) + countService.getUnicomBuilding(resourceType);
        String s = gson.toJson(totalBuilding);
        return s;
    }




    //总的规划
    @ResponseBody
    @RequestMapping(value = "getTotalPlaning",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getTotalPlaning(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int totalPlaning = countService.getTelecomPlaning(resourceType) + countService.getUnicomPlaning(resourceType);
        String s = gson.toJson(totalPlaning);
        return s;
    }



    //总的维护
    @ResponseBody
    @RequestMapping(value = "getTotalMaintenance",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getTotalMaintenance(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int totalMaintenance = countService.getTelecomMaintenance(resourceType) + countService.getUnicomMaintenance(resourceType);
        String s = gson.toJson(totalMaintenance);
        return s;
    }



    //联通租入租出
    @ResponseBody
    @RequestMapping(value = "getUnicomRent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getUnicomRent(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int unicomRentIn = countService.getUnicomRentIn(resourceType);
        int unicomRentOut = countService.getUnicomRentOut(resourceType);
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        map1.put("value",unicomRentIn);
        map1.put("name","RentIn");
        map2.put("value",unicomRentOut);
        map2.put("name","RentOut");

        list.add(map1);
        list.add(map2);
        String json = gson.toJson(list);

        return json;
    }


    //电信租入租出
    @ResponseBody
    @RequestMapping(value = "getTelecomRent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getTelecomRentIn(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int telecomRentIn = countService.getTelecomRentIn(resourceType);
        int telecomRentOut = countService.getTelecomRentOut(resourceType);

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        map1.put("value",telecomRentIn);
        map1.put("name","RentIn");
        map2.put("value",telecomRentOut);
        map2.put("name","RentOut");

        list.add(map1);
        list.add(map2);
        String json = gson.toJson(list);

        return json;

    }


    //根据条件查询联通租入租出详情
    @ResponseBody
    @RequestMapping(value = "getUnicomRentDetailByCondition",produces = "application/json;charset=UTF-8")
    public String getUnicomRentDetailByCondition(String resourceName, String leaseStatus, String status,String resourceType,Integer pageNum, Integer pageSize,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> unicomRentDetailByCondition = countService.getUnicomRentDetailByCondition(resourceName, leaseStatus, status,resourceType,pageNum, pageSize);
        String json = gson.toJson(unicomRentDetailByCondition);
        return json;
    }

    //根据条件查询电信租入租出详情
    @ResponseBody
    @RequestMapping(value = "getTelecomRentDetailByCondition",produces = "application/json;charset=UTF-8")
    public String getTelecomRentDetailByCondition(String resourceName, String leaseStatus, String status,String resourceType,Integer pageNum, Integer pageSize,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> telecomRentDetailByCondition = countService.getTelecomRentDetailByCondition(resourceName, leaseStatus, status,resourceType,pageNum, pageSize);
        String json = gson.toJson(telecomRentDetailByCondition);
        return json;
    }

    //联通和电信基站租赁走势统计
    @ResponseBody
    @RequestMapping(value = "getRentMonth",produces = "application/json;charset=UTF-8")
    public String getRentMonth(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Integer> unicomRentMonth = countService.getUnicomRentMonth(resourceType);
        List<Integer> telecomRentMonth = countService.getTelecomRentMonth(resourceType);
        Map<String,Object> map=new HashMap<>();
        map.put("name","ChinaUnicom");
        map.put("value",unicomRentMonth);
        Map<String,Object> map2=new HashMap<>();
        map2.put("name","ChinaTelecom");
        map2.put("value",telecomRentMonth);
        /*
        * "name":"ChinaUnicom","value":"[]"
        * */
        List<Map<String,Object>> list=new ArrayList<>();
        list.add(map);
        list.add(map2);
        String monthGson = gson.toJson(list);
        return monthGson;
    }

    //北京各区基站数量
    @ResponseBody
    @RequestMapping(value = "getMapCount",produces = "application/json;charset=UTF-8")
    public String getMapCount(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Map<String, Object>> mapCount = countService.getMapCount(resourceType);
        String s = gson.toJson(mapCount);
        return s;
    }

    //第三方统计
    @ResponseBody
    @RequestMapping(value = "getInvestorCount",produces = "application/json;charset=UTF-8")
    public String getInvestorCount(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Object> list = countService.getInvestorCount(resourceType);
        String s = gson.toJson(list);
        return s;
    }

    //规划中基站分布
    @ResponseBody
    @RequestMapping(value = "getPlanStation",produces = "application/json;charset=UTF-8")
    public String getPlanStation(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Map<String, Object>> planStation = countService.getPlanStation(resourceType);
        String s = gson.toJson(planStation);
        return s;
    }

    //第三方中标金额总和
    @ResponseBody
    @RequestMapping(value = "getInvestorCost",produces = "application/json;charset=UTF-8")
    public String getInvestorCost(String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<MyQuotation> investorCost = countService.getInvestorCost(resourceType);
        String s = gson.toJson(investorCost);
        return s;
    }
}


