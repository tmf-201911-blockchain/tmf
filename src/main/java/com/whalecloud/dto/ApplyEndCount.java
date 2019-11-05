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


