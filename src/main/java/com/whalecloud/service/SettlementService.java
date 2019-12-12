package com.whalecloud.service;

import com.whalecloud.domain.MyQuotation;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.domain.RentSettlement;

public interface SettlementService {

    //租入结算
    QueryResult<RentSettlement> findRentInSettlement(String lessee, String status, String resourceType, String resourceName, Integer pageNum, Integer pageSize);
    //租出结算
    QueryResult<RentSettlement> findRentOutSettlement(String lessor, String status, String resourceType, String resourceName, Integer pageNum, Integer pageSize);
    //第三方申请建站,竞标详情显示
    QueryResult<MyQuotation> getInvestorApply(String offeror, String resourceType, String resourceName, Integer pageNum, Integer pageSize);

    //运营商收入
    QueryResult<MyQuotation> getISPSharing(String offeror,String resourceType,String resourceName,Integer pageNum,Integer pageSize);
    //第三方收入
    QueryResult<MyQuotation> getInvestmentSharing(String offeror,String resourceType,String resourceName,Integer pageNum,Integer pageSize);

}
