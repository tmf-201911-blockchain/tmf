package com.whalecloud.dto.enumdto;

/**
 *  单子状态
 *
 * @author zhaoyanac
 * @date 2019/10/14
 */
public enum ApplicationType {
    apply(1,"申请单", "Resource Application"),
    termination(2,"终止单", "Termination Application"),
    investment(3,"建站单", "Investment Application");


    private Integer code;
    private String name;
    private String enName;


    private ApplicationType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    ApplicationType(Integer code, String name, String enName) {
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

    public static ApplicationType getApplyEnum(Integer appCode) throws Exception {
        ApplicationType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ApplicationType appEnum = var1[var3];
            if (appEnum.getCode().equals(appCode)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }


}
