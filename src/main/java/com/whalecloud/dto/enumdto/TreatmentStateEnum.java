package com.whalecloud.dto.enumdto;

/**
 * @author zhaoyanac
 * @date 2019/10/31
 */
public enum TreatmentStateEnum {


    pending(1,"待解决", "pending"),
    solved(2,"已解决", "solved"),
    marked(3,"打过分", "marked");



    private Integer code;
    private String name;
    private String enName;


    TreatmentStateEnum(Integer code, String name, String enName) {
        this.code = code;
        this.name = name;
        this.enName = enName;
    }

    TreatmentStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
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

    public static TreatmentStateEnum getApplyEnum(Integer appCode) throws Exception {
        TreatmentStateEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TreatmentStateEnum appEnum = var1[var3];
            if (appEnum.getCode().equals(appCode)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }



}
