package com.whalecloud.service;



import com.github.pagehelper.Page;
import com.whalecloud.domain.Progress;
import com.whalecloud.domain.QueryResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CountService {
    /**
     * 查询总的基站数量
     * @return
     */
    int getTotalCount(String resourceType);

    /**
     * 查询联通的基站数量
     * @return
     */
    int getUnicomCount(String resourceType);

    /**
     * 查询电信的基站数量
     * @return
     */
    int getTelecomCount(String resourceType);

    /**
     * 查询联通正在使用中的基站数量
     * @return
     */
    int getUnicomUsing(String resourceType);

    /**
     * 查询联通正在建设中的基站数量
     * @return
     */

    int getUnicomBuilding(String resourceType);
    /**
     * 查询联通正在规划中的基站数量
     * @return
     */
    int getUnicomPlaning(String resourceType);
    /**
     * 查询联通正在维护中的基站数量
     * @return
     */
    int getUnicomMaintenance(String resourceType);

    /**
     * 查询电信正在使用中的基站数量
     * @return
     */
    int getTelecomUsing(String resourceType);

    /**
     * 查询电信正在建设中的基站数量
     * @return
     */

    int getTelecomBuilding(String resourceType);
    /**
     * 查询电信正在规划中的基站数量
     * @return
     */
    int getTelecomPlaning(String resourceType);
    /**
     * 查询电信正在维护中的基站数量
     * @return
     */
    int getTelecomMaintenance(String resourceType);

    //联通租入的
    int getUnicomRentIn(String resourceType);
    //联通租出的
    int getUnicomRentOut(String resourceType);

    //电信租入的(电信租入的是联通通过的(或者电信租入的是联通通过的加上移动通过的))
    int getTelecomRentIn(String resourceType);
    //电信租出的
    int getTelecomRentOut(String resourceType);

    List<Integer> getUnicomRentMonth(String resourceType);
    List<Integer> getTelecomRentMonth(String resourceType);

    QueryResult<Progress> getUnicomRentDetailByCondition(String resourceName,String leaseStatus,String status,String resourceType,Integer pageNum, Integer pageSize);

    QueryResult<Progress> getTelecomRentDetailByCondition(String resourceName,String leaseStatus,String status,String resourceType,Integer pageNum, Integer pageSize);

    List<Map<String,Object>> getMapCount(String resourceType);

    List<Map<String, Integer>> getCountByResourceType(String resourceType);

}
