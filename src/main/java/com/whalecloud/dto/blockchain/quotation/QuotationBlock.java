package com.whalecloud.dto.blockchain.quotation;

/**
 *
 * 竞价交互链
 *
 * @author zhaoyanac
 * @date 2019/11/7
 */
public class QuotationBlock {

    private String ResourceID;

    private String Offeror;

    private String QuotationAmount;

    private String QuotationTime;

    public String getResourceID() {
        return ResourceID;
    }

    public void setResourceID(String resourceID) {
        ResourceID = resourceID;
    }

    public String getOfferor() {
        return Offeror;
    }

    public void setOfferor(String offeror) {
        Offeror = offeror;
    }

    public String getQuotationAmount() {
        return QuotationAmount;
    }

    public void setQuotationAmount(String quotationAmount) {
        QuotationAmount = quotationAmount;
    }

    public String getQuotationTime() {
        return QuotationTime;
    }

    public void setQuotationTime(String quotationTime) {
        QuotationTime = quotationTime;
    }
}


