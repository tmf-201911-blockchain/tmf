package com.whalecloud.dto.enumdto;

/**
 * @author zhaoyanac
 * @date 2019/10/14
 */
public enum MaintainLevel {

    high(0,"高","high"),
    middle(1,"中","middle"),
    low(2,"低", "low");

    private Integer code;
    private String name;
    private String enName;


    private MaintainLevel(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    MaintainLevel(Integer code, String name, String enName) {
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

    public static MaintainLevel getByCode(Integer code) throws Exception {
        MaintainLevel[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MaintainLevel appEnum = var1[var3];
            if (appEnum.getCode().equals(code)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }

    public static MaintainLevel getByName(String name) throws Exception {
        MaintainLevel[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MaintainLevel appEnum = var1[var3];
            if (appEnum.getName().equals(name)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }

}
