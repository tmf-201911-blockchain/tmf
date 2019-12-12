package com.whalecloud.serviceimpl;

import com.google.gson.Gson;
import com.whalecloud.domain.QuotationDto;
import com.whalecloud.domain.re.Quotation;
import com.whalecloud.domain.re.QuotationExample;
import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.dto.blockchain.quotation.QuotationBlock;
import com.whalecloud.dto.enumdto.YNEnum;
import com.whalecloud.mapper.re.QuotationMapper;
import com.whalecloud.mapper.re.ResDetailMapper;
import com.whalecloud.service.BidService;
import com.whalecloud.service.ResourcesService;
import com.whalecloud.util.CommonUtil;
import com.whalecloud.util.SeedUtil;
import com.whalecloud.util.httptool.HttpParamers;
import com.whalecloud.util.httptool.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoyanac
 * @date 2019/11/5
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BidServiceImpl implements BidService {

    @Autowired
    QuotationMapper quotationMapper;
    @Autowired
    ResDetailMapper resDetailMapper;
    @Autowired
    ResourcesService resourcesService;


    @Override
    public Quotation add(String resourceId, String userName, BigDecimal quotationAmount) throws Exception {
        Quotation quotation = new Quotation();
        quotation.setId(SeedUtil.getId());
        quotation.setResourceId(resourceId);
        quotation.setOfferor(userName);
        quotation.setQuotationAmount(quotationAmount);
        quotation.setQuotationTime(new Date());
        quotation.setIsBid(YNEnum.NO.getCode());
        quotation.setCreateTime(new Date());
        quotation.setUpdateTime(new Date());
        quotation.setYn(YNEnum.NO.getCode());

        quotationMapper.insertSelective(quotation);


        //区块链--插入竞价记录
        Gson gson = new Gson();
        HttpParamers paramers = HttpParamers.httpPostParamers();
        QuotationBlock quotationBlock = CommonUtil.fitQuotation(quotation);
        String objectOneJson = gson.toJson(quotationBlock);
        paramers.setJsonParamer(objectOneJson);
        String response1 = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response1 = httpService.service("/api/createQuotation", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quotation;
    }


    @Override
    public QuotationDto getAllByResourceId(String resourceId) throws Exception {
        QuotationExample example = new QuotationExample();
        example.setOrderByClause("quotation_time DESC");
        QuotationExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId).andYnEqualTo(YNEnum.NO.getCode());

        List<Quotation> quotations = quotationMapper.selectByExample(example);


        //获取报价最高的人
        Quotation winner = getWinner(resourceId);
        //更新这个基站的中标人
        updateWinner(winner);
        QuotationDto quotationDto = new QuotationDto();
        if (!StringUtils.isEmpty(winner)) {
            quotationDto.setSuccessfulBidder(winner.getOfferor());
            quotationDto.setWinningAmount(winner.getQuotationAmount());
            if (quotations.size() > 0) {
                quotationDto.setQuotations(quotations);
            }
            //更新基站的owner
            ResDetail one = resourcesService.getOne(resourceId);
            one.setOwner(winner.getOfferor());
            ResDetail edit = resourcesService.edit(one);
        }

        return quotationDto;
    }


    @Override
    public QuotationDto getEncryptInfo(String resourceId, String userName) {
        QuotationExample example = new QuotationExample();
        example.setOrderByClause("quotation_time DESC");
        QuotationExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId).andYnEqualTo(YNEnum.NO.getCode());

        List<Quotation> quotations = quotationMapper.selectByExample(example);

        for (Quotation dto : quotations) {

            dto.setEncryptAmount("*****.00");
        }
        Quotation maxOne = getMaxOne(resourceId, userName);

        QuotationDto quotationDto = new QuotationDto();

        quotationDto.setQuotations(quotations);
        quotationDto.setSuccessfulBidder(userName);
        quotationDto.setWinningAmount(maxOne.getQuotationAmount());

        return quotationDto;
    }

    @Override
    public Quotation getMaxOne(String resourceId, String userName) {

        QuotationExample quotationExample = new QuotationExample();
        quotationExample.setOrderByClause("quotation_amount DESC");
        QuotationExample.Criteria criteria1 = quotationExample.createCriteria();
        criteria1.andResourceIdEqualTo(resourceId).andYnEqualTo(YNEnum.NO.getCode()).andOfferorEqualTo(userName);
        //获取资源信息
        List<Quotation> quotationList = quotationMapper.selectByExample(quotationExample);

        if (quotationList.size() > 0) {

            return quotationList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Quotation getWinner(String resourceId) {

        QuotationExample example = new QuotationExample();
        example.setOrderByClause("quotation_amount DESC");
        QuotationExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId);

        List<Quotation> quotations = quotationMapper.selectByExample(example);

        if (quotations.size() > 0) {
            return quotations.get(0);
        } else {
            return null;
        }

    }


    @Override
    public void updateWinner(Quotation winner) {

        if (winner == null) {

        } else {
            winner.setIsBid(YNEnum.YES.getCode());
            winner.setUpdateTime(new Date());


            int i = quotationMapper.updateByPrimaryKey(winner);
        }


    }
}


