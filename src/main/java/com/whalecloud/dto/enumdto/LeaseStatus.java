package com.whalecloud.dto.enumdto;

/**
 *  资源新增  初始化LeaseStatus 为3
 *
 *  资源申请  成功改成2  不成功为3（不改）
 *
 *  终止之前状态为2
 *  资源终止  成功改成3  不成功为2
 *
 *
 *  租赁状态
 * @date 2019/10/14
 */
public enum LeaseStatus {
    RentIn(0,"租入"),
    applying(1,"申请中"),
    RentOut(2,"租出"),
    notUse(3,"待使用");



    private Integer code;
    private String name;


    private LeaseStatus(Integer code, String name) {
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

    public static LeaseStatus getApplyEnum(Integer appCode) throws Exception {
        LeaseStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            LeaseStatus appEnum = var1[var3];
            if (appEnum.getCode().equals(appCode)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }


}
