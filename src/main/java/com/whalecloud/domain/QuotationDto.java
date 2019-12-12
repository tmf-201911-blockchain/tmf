package com.whalecloud.domain;

import com.whalecloud.domain.re.Quotation;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 竞价返回信息
 * @author zhaoyanac
 * @date 2019/11/5
 */
public class QuotationDto {
    /**
     * 获胜者
     *
     */
    private String successfulBidder;
    /**
     * 获胜金额
     *
     */
    private BigDecimal winningAmount;

    private List<Quotation> quotations;

    public String getSuccessfulBidder() {
        return successfulBidder;
    }

    public void setSuccessfulBidder(String successfulBidder) {
        this.successfulBidder = successfulBidder;
    }

    public BigDecimal getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(BigDecimal winningAmount) {
        this.winningAmount = winningAmount;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }
}


