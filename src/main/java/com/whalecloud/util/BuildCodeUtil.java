package com.whalecloud.util;

import java.util.Random;

/**
 *
 * 生成随机6位验证码工具包
 *
 * @author zhaoyanac
 * @date 2019/10/18
 */
public class BuildCodeUtil {

    public String getVerifyCode() {
        int randomInt = new Random().nextInt(999999);

        return String.valueOf(randomInt);
    }
}


