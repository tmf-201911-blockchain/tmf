package com.whalecloud.domain;

import java.util.Date;

/**
 *
 *
 * @author zhaoyanac
 * @date 2019/10/31
 */
public class ReportInfoResponse {
    private String resourceId;

    private String resourceName;

    private String address;

    private Date lastTime;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}


