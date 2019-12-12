package com.whalecloud.domain;

import java.io.Serializable;
import java.util.Date;

public class MyQuotation implements Serializable {
    //d.resource_name,d.resource_id,d.resource_type,q.quotation_amount,q.is_bid
    private String resourceName;
    private String resourceId;
    private String resourceType;
    private String startAmount;
    private String quotationAmount;
    //结算日期
    private Date settlementDate;
    private String settlementDateFormat;
    //是否中标
    private Integer isBid;
    //运营商
    private String ISP;
    //竞价人
    private String offeror;
    private String owner;
    private String lessee;
    //运营商收益
    private String ISPRevenue ;
    //投资者收益
    private String investorRevenue;
    //总收益
    private String totalRevenue;
    //总竞价
    private String sumQuotationAmount;

    private String name;
    private String value;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount;
    }

    public String getQuotationAmount() {
        return quotationAmount;
    }

    public void setQuotationAmount(String quotationAmount) {
        this.quotationAmount = quotationAmount;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementDateFormat() {
        return settlementDateFormat;
    }

    public void setSettlementDateFormat(String settlementDateFormat) {
        this.settlementDateFormat = settlementDateFormat;
    }

    public Integer getIsBid() {
        return isBid;
    }

    public void setIsBid(Integer isBid) {
        this.isBid = isBid;
    }

    public String getISP() {
        return ISP;
    }

    public void setISP(String ISP) {
        this.ISP = ISP;
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public String getISPRevenue() {
        return ISPRevenue;
    }

    public void setISPRevenue(String ISPRevenue) {
        this.ISPRevenue = ISPRevenue;
    }

    public String getInvestorRevenue() {
        return investorRevenue;
    }

    public void setInvestorRevenue(String investorRevenue) {
        this.investorRevenue = investorRevenue;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getSumQuotationAmount() {
        return sumQuotationAmount;
    }

    public void setSumQuotationAmount(String sumQuotationAmount) {
        this.sumQuotationAmount = sumQuotationAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
