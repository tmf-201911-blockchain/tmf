package com.whalecloud.dto.enumdto;

/**
 *
 * 设备状态
 *
 * @author zhaoyanac
 * @date 2019/10/14
 */
public enum EquipmentStatus {

    using(0,"使用中", "in use"),
    building(1,"建设中", "under construction"),
    programming(2,"规划中", "Planning"),
    maintaining(3,"维护中", "maintenance");


    private Integer code;
    private String name;
    private String enName;


    EquipmentStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    EquipmentStatus(Integer code, String name, String enName) {
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

    public static EquipmentStatus getByCode(Integer appCode) throws Exception {
        EquipmentStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EquipmentStatus appEnum = var1[var3];
            if (appEnum.getCode().equals(appCode)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }

    public static EquipmentStatus getByName(String name) throws Exception {
        EquipmentStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EquipmentStatus appEnum = var1[var3];
            if (appEnum.getName().equals(name)) {
                return appEnum;
            }
        }

        throw new Exception("方法名不存在");
    }


}
