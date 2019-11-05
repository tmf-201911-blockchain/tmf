package com.whalecloud.service;

import com.whalecloud.domain.QueryResult;
import com.whalecloud.domain.RentSettlement;

public interface SettlementService {

    //租入结算
    QueryResult<RentSettlement> findRentInSettlement(String lessee, String status, String resourceType, String resourceName, Integer pageNum, Integer pageSize);
    //租出结算
    QueryResult<RentSettlement> findRentOutSettlement(String lessor, String status, String resourceType, String resourceName, Integer pageNum, Integer pageSize);

}
