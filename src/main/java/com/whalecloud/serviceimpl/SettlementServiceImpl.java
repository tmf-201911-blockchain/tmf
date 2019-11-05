package com.whalecloud.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.domain.RentSettlement;
import com.whalecloud.mapper.re.SettlementMapper;
import com.whalecloud.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Double totalActualRate = this.getTotalActualRate(result);
        long total = rentInSettlement.getTotal();
        int pages = rentInSettlement.getPages();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        queryResult.setTotalActualRate(totalActualRate);
        return queryResult;
    }

    //租出结算
    @Override
    public QueryResult<RentSettlement> findRentOutSettlement(String lessor, String status, String resourceType, String resourceName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        QueryResult<RentSettlement> queryResult = new QueryResult<>();
        Page<RentSettlement> rentOutSettlement = settlementMapper.findRentOutSettlement(lessor, status, resourceType, resourceName);

        List<RentSettlement> result = rentOutSettlement.getResult();

        Double totalActualRate = this.getTotalActualRate(result);

        long total = rentOutSettlement.getTotal();
        int pages = rentOutSettlement.getPages();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        queryResult.setTotalActualRate(totalActualRate);
        return queryResult;
    }

    //求评分及实际费率和每页总费用
    private Double getTotalActualRate(List<RentSettlement> result){
        //每页的总费率
        Double totalActualRate=0.0;
        for (RentSettlement settlement : result) {
            Double actualRate=0.0;
            String resourceId = settlement.getResourceId();
            //根据资源id查询评分
            Integer integer = settlementMapper.getStar(resourceId);
            //评分为0,说明没有人评分,设置评分为0.0,前端显示为--
            if (integer==0){
                settlement.setStar(0.0);
                actualRate = settlement.getRate();
                settlement.setActualRate(actualRate);
            }else {
                //评分不为零,说明有人评分,取其平均值为综合评分
                Double avgStar = settlementMapper.getAvgStar(resourceId);
                settlement.setStar(avgStar);
                Double rate = settlement.getRate();
                //实际费率
                actualRate = rate - rate * ((5 - avgStar) / 1000);
                settlement.setActualRate(actualRate);
            }
            totalActualRate+=actualRate;
        }
        return  totalActualRate;
    }
}
