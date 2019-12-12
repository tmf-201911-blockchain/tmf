package com.whalecloud.domain;

import java.util.Date;

/**
 *
 * 在基础上增加resourceName
 * @author zhaoyanac
 * @date 2019/11/2
 */
public class ReportResDto {
    private Long id;

    private String resourceId;

    private String suggestion;

    private String path;

    private Date reportTime;

    private Date createTime;

    private Date updateTime;

    private String phone;

    private String star;

    private Integer treatmentState;

    private Integer yn;

    private String taskId;

    private String resourceName;

    private String areaCode;

    private String resourceType;

    private Integer importance;

    private String latitude;

    private String longitude;

    private String address;

    private String resourcePath;

    private Integer status;

    /**
     * 上传速率
     */
    private String UploadRate;
    /**
     * 下载速率
     */
    private String DownloadRate;
    /**
     * 网络延迟
     */
    private String NetworkDelay;
    /**
     * 调用连接速率
     */
    private String CallingConnectionRate;
    /**
     * 语音拨号速率
     */
    private String VoiceDropRate;

    public String getUploadRate() {
        return UploadRate;
    }

    public void setUploadRate(String uploadRate) {
        UploadRate = uploadRate;
    }

    public String getDownloadRate() {
        return DownloadRate;
    }

    public void setDownloadRate(String downloadRate) {
        DownloadRate = downloadRate;
    }

    public String getNetworkDelay() {
        return NetworkDelay;
    }

    public void setNetworkDelay(String networkDelay) {
        NetworkDelay = networkDelay;
    }

    public String getCallingConnectionRate() {
        return CallingConnectionRate;
    }

    public void setCallingConnectionRate(String callingConnectionRate) {
        CallingConnectionRate = callingConnectionRate;
    }

    public String getVoiceDropRate() {
        return VoiceDropRate;
    }

    public void setVoiceDropRate(String voiceDropRate) {
        VoiceDropRate = voiceDropRate;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Integer getTreatmentState() {
        return treatmentState;
    }

    public void setTreatmentState(Integer treatmentState) {
        this.treatmentState = treatmentState;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
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
}


