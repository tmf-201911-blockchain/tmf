package com.whalecloud.dto.enumdto;

/**
 *  审核状态
 *
 * @author zhaoyanac
 * @date 2019/10/14
 */
public enum ApplyStatus {
    awaiting(0,"审核中", "pending"),
    refused(1,"拒绝", "refused"),
    pass(2,"通过", "approved");


    private Integer code;
    private String name;
    private String enName;


    private ApplyStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    ApplyStatus(Integer code, String name, String enName) {
        this.code = code;
        this.name = name;
        this.enName = enName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public static ApplyStatus getApplyEnum(Integer appCode) throws Exception {
        ApplyStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ApplyStatus appEnum = var1[var3];
            if (appEnum.getCode().equals(appCode)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }


}
