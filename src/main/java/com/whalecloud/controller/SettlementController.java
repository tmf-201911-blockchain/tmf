package com.whalecloud.controller;


import com.google.gson.Gson;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.domain.RentSettlement;
import com.whalecloud.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rent/")
public class SettlementController {
    Gson gson=new Gson();

    @Autowired
    private SettlementService settlementService;

    //租入结算
    @ResponseBody
    @RequestMapping(value = "findRentInSettlement",produces = "application/json;charset=utf-8")
    public String findRentInSettlement(String lessee, String status, String resourceType, String resourceName,Integer pageNum,Integer pageSize, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<RentSettlement> rentInSettlement = settlementService.findRentInSettlement(lessee, status, resourceType,resourceName, pageNum, pageSize);
        String s = gson.toJson(rentInSettlement);
        return s;
    }

    //租出结算
    @ResponseBody
    @RequestMapping(value = "findRentOutSettlement",produces = "application/json;charset=utf-8")
    public String findRentOutSettlement(String lessor, String status, String resourceType, String resourceName,Integer pageNum,Integer pageSize, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<RentSettlement> rentOutSettlement = settlementService.findRentOutSettlement(lessor, status, resourceType,resourceName,pageNum, pageSize);
        String s = gson.toJson(rentOutSettlement);
        return s;
    }
}
