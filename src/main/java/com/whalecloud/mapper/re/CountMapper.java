package com.whalecloud.mapper.re;

import com.github.pagehelper.Page;
import com.whalecloud.domain.Progress;
import org.apache.ibatis.annotations.Param;

public interface CountMapper {

    int getTotalCount(@Param("resourceType") String resourceType);

    int getUnicomCount(@Param("resourceType") String resourceType);

    int getTelecomCount(@Param("resourceType") String resourceType);

    int getUnicomUsing(@Param("resourceType") String resourceType);

    int getUnicomBuilding(@Param("resourceType") String resourceType);

    int getUnicomPlaning(@Param("resourceType") String resourceType);

    int getUnicomMaintenance(@Param("resourceType") String resourceType);

    int getTelecomUsing(@Param("resourceType") String resourceType);

    int getTelecomBuilding(@Param("resourceType") String resourceType);

    int getTelecomPlaning(@Param("resourceType") String resourceType);

    int getTelecomMaintenance(@Param("resourceType") String resourceType);

    int getUnicomRentIn(@Param("resourceType") String resourceType);
    int getUnicomRentOut(@Param("resourceType") String resourceType);

    int getTelecomRentIn(@Param("resourceType") String resourceType);
    int getTelecomRentOut(@Param("resourceType") String resourceType);

    int getUnicomRentInJanuary(@Param("resourceType") String resourceType);
    int getUnicomRentInFebruary(@Param("resourceType") String resourceType);
    int getUnicomRentInMarch(@Param("resourceType") String resourceType);
    int getUnicomRentInApril(@Param("resourceType") String resourceType);
    int getUnicomRentInMay(@Param("resourceType") String resourceType);
    int getUnicomRentInJune(@Param("resourceType") String resourceType);
    int getUnicomRentInJuly(@Param("resourceType") String resourceType);
    int getUnicomRentInAugust(@Param("resourceType") String resourceType);
    int getUnicomRentInSeptember(@Param("resourceType") String resourceType);
    int getUnicomRentInOctober(@Param("resourceType") String resourceType);
    int getUnicomRentInNovember(@Param("resourceType") String resourceType);
    int getUnicomRentInDecember(@Param("resourceType") String resourceType);

    int getTelecomRentInJanuary(@Param("resourceType") String resourceType);
    int getTelecomRentInFebruary(@Param("resourceType") String resourceType);
    int getTelecomRentInMarch(@Param("resourceType") String resourceType);
    int getTelecomRentInApril(@Param("resourceType") String resourceType);
    int getTelecomRentInMay(@Param("resourceType") String resourceType);
    int getTelecomRentInJune(@Param("resourceType") String resourceType);
    int getTelecomRentInJuly(@Param("resourceType") String resourceType);
    int getTelecomRentInAugust(@Param("resourceType") String resourceType);
    int getTelecomRentInSeptember(@Param("resourceType") String resourceType);
    int getTelecomRentInOctober(@Param("resourceType") String resourceType);
    int getTelecomRentInNovember(@Param("resourceType") String resourceType);
    int getTelecomRentInDecember(@Param("resourceType") String resourceType);

    Page<Progress> getUnicomRentDetailByCondition(@Param("resourceName") String resourceName,@Param("leaseStatus") String leaseStatus, @Param("status")String status,@Param("resourceType") String resourceType);
    Page<Progress> getTelecomRentDetailByCondition(@Param("resourceName") String resourceName,@Param("leaseStatus") String leaseStatus, @Param("status")String status,@Param("resourceType") String resourceType);

    int getDC(@Param("resourceType") String resourceType);
    int getDCUse(@Param("resourceType") String resourceType);
    int getDCBuild(@Param("resourceType") String resourceType);
    int getDCPlan(@Param("resourceType") String resourceType);
    int getDCMaintain(@Param("resourceType") String resourceType);

    int getYQ(@Param("resourceType") String resourceType);
    int getYQUse(@Param("resourceType") String resourceType);
    int getYQBuild(@Param("resourceType") String resourceType);
    int getYQPlan(@Param("resourceType") String resourceType);
    int getYQMaintain(@Param("resourceType") String resourceType);
    int getHR(@Param("resourceType") String resourceType);
    int getHRUse(@Param("resourceType") String resourceType);
    int getHRBuild(@Param("resourceType") String resourceType);
    int getHRPlan(@Param("resourceType") String resourceType);
    int getHRMaintain(@Param("resourceType") String resourceType);

    int getMY(@Param("resourceType") String resourceType);
    int getMYUse(@Param("resourceType") String resourceType);
    int getMYBuild(@Param("resourceType") String resourceType);
    int getMYPlan(@Param("resourceType") String resourceType);
    int getMYMaintain(@Param("resourceType") String resourceType);

    int getCP(@Param("resourceType") String resourceType);
    int getCPUse(@Param("resourceType") String resourceType);
    int getCPBuild(@Param("resourceType") String resourceType);
    int getCPPlan(@Param("resourceType") String resourceType);
    int getCPMaintain(@Param("resourceType") String resourceType);

    int getSY(@Param("resourceType") String resourceType);
    int getSYUse(@Param("resourceType") String resourceType);
    int getSYBuild(@Param("resourceType") String resourceType);
    int getSYPlan(@Param("resourceType") String resourceType);
    int getSYMaintain(@Param("resourceType") String resourceType);

    int getPG(@Param("resourceType") String resourceType);
    int getPGUse(@Param("resourceType") String resourceType);
    int getPGBuild(@Param("resourceType") String resourceType);
    int getPGPlan(@Param("resourceType") String resourceType);
    int getPGMaintain(@Param("resourceType") String resourceType);

    int getMTG(@Param("resourceType") String resourceType);
    int getMTGUse(@Param("resourceType") String resourceType);
    int getMTGBuild(@Param("resourceType") String resourceType);
    int getMTGPlan(@Param("resourceType") String resourceType);
    int getMTGMaintain(@Param("resourceType") String resourceType);

    int getHD(@Param("resourceType") String resourceType);
    int getHDUse(@Param("resourceType") String resourceType);
    int getHDBuild(@Param("resourceType") String resourceType);
    int getHDPlan(@Param("resourceType") String resourceType);
    int getHDMaintain(@Param("resourceType") String resourceType);

    int getSJS(@Param("resourceType") String resourceType);
    int getSJSUse(@Param("resourceType") String resourceType);
    int getSJSBuild(@Param("resourceType") String resourceType);
    int getSJSPlan(@Param("resourceType") String resourceType);
    int getSJSMaintain(@Param("resourceType") String resourceType);

    int getXC(@Param("resourceType") String resourceType);
    int getXCUse(@Param("resourceType") String resourceType);
    int getXCBuild(@Param("resourceType") String resourceType);
    int getXCPlan(@Param("resourceType") String resourceType);
    int getXCMaintain(@Param("resourceType") String resourceType);

    int getCY(@Param("resourceType") String resourceType);
    int getCYUse(@Param("resourceType") String resourceType);
    int getCYBuild(@Param("resourceType") String resourceType);
    int getCYPlan(@Param("resourceType") String resourceType);
    int getCYMaintain(@Param("resourceType") String resourceType);

    int getTZ(@Param("resourceType") String resourceType);
    int getTZUse(@Param("resourceType") String resourceType);
    int getTZBuild(@Param("resourceType") String resourceType);
    int getTZPlan(@Param("resourceType") String resourceType);
    int getTZMaintain(@Param("resourceType") String resourceType);

    int getDX(@Param("resourceType") String resourceType);
    int getDXUse(@Param("resourceType") String resourceType);
    int getDXBuild(@Param("resourceType") String resourceType);
    int getDXPlan(@Param("resourceType") String resourceType);
    int getDXMaintain(@Param("resourceType") String resourceType);

    int getFS(@Param("resourceType") String resourceType);
    int getFSUse(@Param("resourceType") String resourceType);
    int getFSBuild(@Param("resourceType") String resourceType);
    int getFSPlan(@Param("resourceType") String resourceType);
    int getFSMaintain(@Param("resourceType") String resourceType);

    int getFT(@Param("resourceType") String resourceType);
    int getFTUse(@Param("resourceType") String resourceType);
    int getFTBuild(@Param("resourceType") String resourceType);
    int getFTPlan(@Param("resourceType") String resourceType);
    int getFTMaintain(@Param("resourceType") String resourceType);

}
