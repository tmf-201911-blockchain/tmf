package com.whalecloud.domain;

import java.util.Date;

/**
 * @author zhaoyanac
 * @date 2019/11/7
 */
public class ReportResWithSpeed {
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
}


