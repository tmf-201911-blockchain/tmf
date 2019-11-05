package com.whalecloud.service;

/**
 *
 * 验证码平台
 *
 * @author zhaoyanac
 * @date 2019/10/31
 */
public interface CodeService {

    /**
     *
     * 添加
     *
     * @param phone
     * @param code
     */
    void add(String phone, String code);


    /**
     *
     * 验证码是否正确
     *
     * @param phone
     * @param code
     * @return
     */
    Boolean isTrue(String phone, String code) throws Exception;

}


