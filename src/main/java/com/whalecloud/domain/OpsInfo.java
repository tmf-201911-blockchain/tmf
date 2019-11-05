package com.whalecloud.domain;

import java.util.Date;

/**
 *
 * 维护信息的表
 *
 * @author zhaoyanac
 * @date 2019/10/31
 */
public class OpsInfo {
    /**
     * 任务单号
     *
     */
    private String taskId;
    /**
     * 资源名称
     */
    private String resourceName;

    private String resourceId;
    /**
     * 评论
     *
     */
    private String reportContent;
    /**
     * 地区
     *
     */
    private String areaCode;

    /**
     *
     * 手机号
     */
    private String feedbackUser;
    /**
     *
     * 资源类型
     *
     */
    private String resourceType;

    private Date reportingTime;

    /**
     * 是否评价过
     *
     */
    private String isEvaluate;

    private Integer treatmentState;

    /**
     *
     * 评分
     *
     */
    private String star;

    private Integer pageNum;

    private Integer pageSize;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getFeedbackUser() {
        return feedbackUser;
    }

    public void setFeedbackUser(String feedbackUser) {
        this.feedbackUser = feedbackUser;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Date getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(Date reportingTime) {
        this.reportingTime = reportingTime;
    }

    public String getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(String isEvaluate) {
        this.isEvaluate = isEvaluate;
    }

    public Integer getTreatmentState() {
        return treatmentState;
    }

    public void setTreatmentState(Integer treatmentState) {
        this.treatmentState = treatmentState;
    }
}


