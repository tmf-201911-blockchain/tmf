package com.whalecloud.dto.enumdto;

/**
 * 是否删除
 *
 * @author zhaoyanac
 * @date 2019/10/17
 */
public enum YNEnum {

    YES(1,"是", "YES"),
    NO(0,"否", "NO");

    private Integer code;
    private String name;
    private String enName;

    YNEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    YNEnum(Integer code, String name, String enName) {
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

    public static YNEnum getApplyEnum(String appCode) throws Exception {
        YNEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            YNEnum appEnum = var1[var3];
            if (appEnum.getCode().equals(appCode)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }
}


