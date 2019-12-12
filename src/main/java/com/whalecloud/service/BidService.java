package com.whalecloud.service;

import com.whalecloud.domain.QuotationDto;
import com.whalecloud.domain.re.Quotation;

import java.math.BigDecimal;

/**
 * @author zhaoyanac
 * @date 2019/11/5
 */
public interface BidService {

    /**
     *
     * 添加竞价
     *
     * @param resourceId  资源Id
     * @param userName  用户名
     * @param quotationAmount   金额
     * @return
     */
    Quotation add(String resourceId, String userName, BigDecimal quotationAmount) throws Exception;

    /**
     * 通过资源ID，查询出所有的竞价
     *
     * @param resourceId
     * @return
     */
    QuotationDto getAllByResourceId(String resourceId) throws Exception;


    /**
     *
     * 通过资源ID，和用户名获取当前竞价信息
     *
     * @param resourceId
     * @param userName
     * @return
     */
    QuotationDto getEncryptInfo(String resourceId, String userName);

    /**
     *
     * 得到当前用户报价最高的
     *
     * @param resourceId
     * @param userName
     */
    Quotation getMaxOne(String resourceId, String userName);


    /**
     * 获取获胜者
     *
     * @param resourceId
     * @return
     */
    Quotation getWinner(String resourceId);


    /**
     *
     * 更新中标人
     *
     * @param winner
     */
    void updateWinner(Quotation winner);


}

