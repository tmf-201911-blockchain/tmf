package com.whalecloud.controller;

import com.google.gson.Gson;
import com.whalecloud.domain.QuotationDto;
import com.whalecloud.domain.re.Quotation;
import com.whalecloud.dto.Respsonse;
import com.whalecloud.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 *
 * 竞价
 *
 * @author zhaoyanac
 * @date 2019/11/5
 */
@Controller
@RequestMapping("/bid/")
public class BidController {


    private static Logger logger = LoggerFactory.getLogger(BidController.class);

    @Autowired
    BidService bidService;

    /**
     * 个人竞价
     *
     * @param response
     * @param resourceId
     * @param userName
     * @param quotationAmount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "auction", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String auction(HttpServletResponse response, String resourceId, String userName, BigDecimal quotationAmount) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        try {
            Quotation dto = bidService.add(resourceId, userName, quotationAmount);

            String json = gson.toJson(new Respsonse("200", "OK", dto));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }

    /**
     *
     * 竞价--信息展示
     *
     * @param response
     * @param resourceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllByResourceId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getAllByResourceId(HttpServletResponse response, String resourceId) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        try {
            QuotationDto dto = bidService.getAllByResourceId(resourceId);

            String json = gson.toJson(new Respsonse("200", "OK", dto));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }

    }


    /**
     *
     * 得到加密的竞价信息展示
     *
     * @param response
     * @param resourceId
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getEncryptInfo", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getEncryptInfo(HttpServletResponse response, String resourceId, String userName) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        try {
            QuotationDto dto = bidService.getEncryptInfo(resourceId, userName);

            String json = gson.toJson(new Respsonse("200", "OK", dto));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }



}


