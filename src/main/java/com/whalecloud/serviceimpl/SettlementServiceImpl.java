package com.whalecloud.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whalecloud.domain.MyQuotation;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.domain.RentSettlement;
import com.whalecloud.mapper.re.SettlementMapper;
import com.whalecloud.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    private SettlementMapper settlementMapper;


    //租入结算
    @Override
    public QueryResult<RentSettlement> findRentInSettlement(String lessee, String status, String resourceType, String resourceName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        QueryResult<RentSettlement> queryResult = new QueryResult<>();
        Page<RentSettlement> rentInSettlement = settlementMapper.findRentInSettlement(lessee, status, resourceType, resourceName);

        List<RentSettlement> result = rentInSettlement.getResult();
        if (result == null) {
            return new QueryResult<RentSettlement>();
        }
        String totalActualRate = this.getTotalActualRate(result);
        long total = rentInSettlement.getTotal();
        int pages = rentInSettlement.getPages();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        if (".00".equals(totalActualRate)){
            queryResult.setTotalActualRate("0.00");
        }else {
            queryResult.setTotalActualRate(totalActualRate);
        }
        return queryResult;
    }

    //租出结算
    @Override
    public QueryResult<RentSettlement> findRentOutSettlement(String lessor, String status, String resourceType, String resourceName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        QueryResult<RentSettlement> queryResult = new QueryResult<>();
        Page<RentSettlement> rentOutSettlement = settlementMapper.findRentOutSettlement(lessor, status, resourceType, resourceName);

        List<RentSettlement> result = rentOutSettlement.getResult();
        if (result == null) {
            return new QueryResult<RentSettlement>();
        }
        String totalActualRate = this.getTotalActualRate(result);

        long total = rentOutSettlement.getTotal();
        int pages = rentOutSettlement.getPages();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        if (".00".equals(totalActualRate)){
            queryResult.setTotalActualRate("0.00");
        }else {
            queryResult.setTotalActualRate(totalActualRate);
        }
        return queryResult;
    }

    //求评分及实际费率和每页总费用
    private String getTotalActualRate(List<RentSettlement> result){
        DecimalFormat df=new DecimalFormat("#.00");
        //每页的总费率
        Double totalActualRate=0.0;
        for (RentSettlement settlement : result) {
            Double actualRate=0.00;
            String resourceId = settlement.getResourceId();
            //根据资源id查询评分
            Integer star = settlementMapper.getStar(resourceId);
            //评分为0,说明没有人评分,设置评分为0.0,前端显示为--
            if (star==0){
                settlement.setStar(0.0);
                actualRate = settlement.getRate();
                if (actualRate==0){
                    settlement.setActualRate("0.00");
                    settlement.setRatePlus("0.00");
                }else {
                    String format = df.format(actualRate);
                    settlement.setActualRate(format);
                    settlement.setRatePlus(format);
                }
            }else {
                //评分不为零,说明有人评分,取其平均值为综合评分
                Double avgStar = settlementMapper.getAvgStar(resourceId);
                settlement.setStar(avgStar);
                Double rate = settlement.getRate();
                //实际费率
                actualRate = rate - rate * ((5 - avgStar) / 1000);

                String format = df.format(actualRate);
                settlement.setActualRate(format);
            }
            totalActualRate+=actualRate;
        }
        String format = df.format(totalActualRate);
        return  format;
    }

    @Override
    public QueryResult<MyQuotation> getInvestorApply(String offeror, String resourceType, String resourceName,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        Page<MyQuotation> investorApply = settlementMapper.getInvestorApply(offeror, resourceType, resourceName);
        List<MyQuotation> result = investorApply.getResult();

        if (result == null) {
            return new QueryResult<MyQuotation>();
        }
        long total = investorApply.getTotal();
        int pages = investorApply.getPages();
        QueryResult<MyQuotation> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);

        return queryResult;
    }

    //运营商收入
    @Override
    public QueryResult<MyQuotation> getISPSharing(String offeror,String resourceType, String resourceName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        Page<MyQuotation> ISPSharing =settlementMapper.getISPSharing(offeror,resourceType, resourceName);

        List<MyQuotation> result = ISPSharing.getResult();
        if (result == null) {
            return new QueryResult<MyQuotation>();
        }
        String totalActualRate = this.getTotalActualRate2(result);

        long total = ISPSharing.getTotal();
        int pages = ISPSharing.getPages();
        QueryResult<MyQuotation> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        if (".00".equals(totalActualRate)){
            queryResult.setTotalActualRate("0.00");
        }else {
            queryResult.setTotalActualRate(totalActualRate);
        }
        return queryResult;
    }

    //第三方收入
    @Override
    public QueryResult<MyQuotation> getInvestmentSharing(String offeror,String resourceType, String resourceName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<MyQuotation> investmentSharing = settlementMapper.getInvestmentSharing(offeror,resourceType, resourceName);

        List<MyQuotation> result = investmentSharing.getResult();
        String totalActualRate = this.getTotalActualRate2(result);
        if (result == null) {
            return new QueryResult<MyQuotation>();
        }
        long total = investmentSharing.getTotal();
        int pages = investmentSharing.getPages();
        QueryResult<MyQuotation> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        if (".00".equals(totalActualRate)){
            queryResult.setTotalActualRate("0.00");
        }else {
            queryResult.setTotalActualRate(totalActualRate);
        }
        return queryResult;
    }

    private String getTotalActualRate2(List<MyQuotation> result){
        DecimalFormat df=new DecimalFormat("#.00");
        //每页的总费率
        Double totalActualRate=0.0;
        for (MyQuotation myQuotation : result) {

            String ispRevenue = myQuotation.getISPRevenue();

            if ("".equals(ispRevenue)){
                ispRevenue="0.00";
            }
            double v1 = Double.parseDouble(ispRevenue)*0.3;
            String investorRevenue = myQuotation.getInvestorRevenue();
            if ("".equals(investorRevenue)){
                investorRevenue="0.00";
            }
            double v2 = Double.parseDouble(investorRevenue)*0.3;
            String format1 = df.format(v1);
            if (v1==0.0){
                format1="0.00";
            }
            String format2 = df.format(v2);
            if (v2==0.0){
                format2="0.00";
            }


            Double totalRevenue=0.00;
            String owner = myQuotation.getOwner();
            if ("ChinaUnicom".equals(owner)||"ChinaTelecom".equals(owner)){
                totalRevenue=v1;
                myQuotation.setInvestorRevenue("0.00");
            }else {
                totalRevenue=v1+v2;
                myQuotation.setInvestorRevenue(format2);
            }

            //日期格式化
            Date createDate = myQuotation.getSettlementDate();
            Calendar instance = Calendar.getInstance();
            instance.setTime(createDate);
            instance.add(Calendar.MONTH,-3);
            Date time = instance.getTime();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = format.format(time);

            myQuotation.setSettlementDateFormat(formatDate);
            myQuotation.setISPRevenue(format1);
            String format3 = df.format(totalRevenue);
            myQuotation.setTotalRevenue(format3);

            totalActualRate+=totalRevenue;
        }
        String format = df.format(totalActualRate);
        return format;
    }
}
