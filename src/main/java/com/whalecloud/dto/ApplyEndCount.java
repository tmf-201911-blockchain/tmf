package com.whalecloud.dto;

/**
 *
 * 申请和终止的过的次数
 *
 * @author zhaoyanac
 * @date 2019/10/25
 */
public class ApplyEndCount {

    private int applyCount;

    private String owner;

    private String isp;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}


