package com.whalecloud.util;

/**
 * 返回的实体
 *
 * @author zhaoyanac
 * @date 2019/10/21
 */
public class Result {
    //判断结果
    private boolean success;
    //返回信息
    private String message;


    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


