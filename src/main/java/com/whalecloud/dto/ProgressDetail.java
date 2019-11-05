package com.whalecloud.dto;

import java.io.Serializable;
import java.util.Date;

public class ProgressDetail implements Serializable {
          /*{
        "TaskId": "10001",
        "ResourceId": "WFJM01",
        "ResourceName": "Wangfujing Street marco base station",
        "Lessor": "CU",
        "Lessee": "CT",
        "ApplicationType": "rescourse application",
        "Progress": "refused",
        "ApplicaitonTime": "22/10/2019",
        "ProcessTime": "22/10/2019",
        "EffectiveTime": "25/10/2019"
        }*/
    private String TaskId;
    private String ResourceId;
    private String ResourceName;
    private String Lessor;
    private String Lessee;
    private String ApplicationType;
    private String Progress;
    private String ApplicationTime;
    private String ProcessTime;
    private String EffectiveTime;

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getResourceId() {
        return ResourceId;
    }

    public void setResourceId(String resourceId) {
        ResourceId = resourceId;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public void setResourceName(String resourceName) {
        ResourceName = resourceName;
    }

    public String getLessor() {
        return Lessor;
    }

    public void setLessor(String lessor) {
        Lessor = lessor;
    }

    public String getLessee() {
        return Lessee;
    }

    public void setLessee(String lessee) {
        Lessee = lessee;
    }

    public String getApplicationType() {
        return ApplicationType;
    }

    public void setApplicationType(String applicationType) {
        ApplicationType = applicationType;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String progress) {
        Progress = progress;
    }

    public String getApplicationTime() {
        return ApplicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        ApplicationTime = applicationTime;
    }

    public String getProcessTime() {
        return ProcessTime;
    }

    public void setProcessTime(String processTime) {
        ProcessTime = processTime;
    }

    public String getEffectiveTime() {
        return EffectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        EffectiveTime = effectiveTime;
    }
}
