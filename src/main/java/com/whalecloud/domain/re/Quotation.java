package com.whalecloud.domain.re;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Quotation implements Serializable {
    private Long id;

    private String resourceId;

    private String offeror;

    private BigDecimal quotationAmount;

    private Date quotationTime;

    private Integer isBid;

    private Date createTime;

    private Date updateTime;

    private Integer yn;

    /**
     * 加密金额
     *
     */
    private String encryptAmount;

    private static final long serialVersionUID = 1L;

    public String getEncryptAmount() {
        return encryptAmount;
    }

    public void setEncryptAmount(String encryptAmount) {
        this.encryptAmount = encryptAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror == null ? null : offeror.trim();
    }

    public BigDecimal getQuotationAmount() {
        return quotationAmount;
    }

    public void setQuotationAmount(BigDecimal quotationAmount) {
        this.quotationAmount = quotationAmount;
    }

    public Date getQuotationTime() {
        return quotationTime;
    }

    public void setQuotationTime(Date quotationTime) {
        this.quotationTime = quotationTime;
    }

    public Integer getIsBid() {
        return isBid;
    }

    public void setIsBid(Integer isBid) {
        this.isBid = isBid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}