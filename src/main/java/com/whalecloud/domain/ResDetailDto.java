package com.whalecloud.domain;

import com.whalecloud.domain.re.ReportRes;

import java.util.Date;
import java.util.List;

/**
 *
 * 基站详情--带评价
 *
 * @author zhaoyanac
 * @date 2019/11/2
 */
public class ResDetailDto {

    private Long id;

    private String resourceId;

    private String resourceName;

    private String resourceType;

    private String towerId;

    private String owner;

    private String lessor;

    private String lessee;

    private Date effectiveTime;

    private String nep;

    private Integer status;

    private Integer importance;

    private Double rate;

    private String isp;

    private String latitude;

    private String longitude;

    private String areaCode;

    private String address;

    private Integer leaseStatus;

    private String path;

    private Date createTime;

    private Date updateTime;

    private Integer yn;

    private String investmentCost;

    private Date deadline;

    private Long quotationDeadline;

    private List<ReportRes> reportRes;

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public Long getQuotationDeadline() {
        return quotationDeadline;
    }

    public void setQuotationDeadline(Long quotationDeadline) {
        this.quotationDeadline = quotationDeadline;
    }

    public String getInvestmentCost() {
        return investmentCost;
    }

    public void setInvestmentCost(String investmentCost) {
        this.investmentCost = investmentCost;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getTowerId() {
        return towerId;
    }

    public void setTowerId(String towerId) {
        this.towerId = towerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(Integer leaseStatus) {
        this.leaseStatus = leaseStatus;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public List<ReportRes> getReportRes() {
        return reportRes;
    }

    public void setReportRes(List<ReportRes> reportRes) {
        this.reportRes = reportRes;
    }
}


