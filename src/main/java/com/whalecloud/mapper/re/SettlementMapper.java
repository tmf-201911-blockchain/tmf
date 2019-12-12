package com.whalecloud.mapper.re;


import com.github.pagehelper.Page;
import com.whalecloud.domain.MyQuotation;
import com.whalecloud.domain.RentSettlement;
import org.apache.ibatis.annotations.Param;

/**
 *
 * 结算
 *
 * @date 2019/11/01
 */
public interface SettlementMapper {
    //租入结算
    Page<RentSettlement> findRentInSettlement(@Param("lessee")String lessee, @Param("status")String status, @Param("resourceType")String resourceType, @Param("resourceName")String resourceName);
    //租出结算
    Page<RentSettlement> findRentOutSettlement(@Param("lessor")String lessor, @Param("status")String status, @Param("resourceType")String resourceType, @Param("resourceName")String resourceName);
    //综合评分
    Double getAvgStar(@Param("resourceId")String resourceId);
    //查询评分
    Integer getStar(@Param("resourceId")String resourceId);

    //第三方申请建站,竞标详情显示
    Page<MyQuotation> getInvestorApply(@Param("offeror")String offeror, @Param("resourceType")String resourceType, @Param("resourceName")String resourceName);

    //运营商收入
    Page<MyQuotation> getISPSharing(@Param("offeror")String offeror,@Param("resourceType")String resourceType,@Param("resourceName")String resourceName);

    //第三方收入
    Page<MyQuotation> getInvestmentSharing(@Param("offeror")String offeror,@Param("resourceType")String resourceType,@Param("resourceName")String resourceName);

}
