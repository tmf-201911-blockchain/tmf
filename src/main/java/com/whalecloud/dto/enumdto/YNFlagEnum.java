package com.whalecloud.dto.enumdto;

/**
 *
 * 是否成功
 * @author zhaoyanac
 * @date 2019/10/28
 */
public enum YNFlagEnum {
    OK(1,"OK"),
    ERROR(2,"ERROR");

    private Integer code;
    private String name;

    YNFlagEnum(Integer code, String name) {
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
    }}


