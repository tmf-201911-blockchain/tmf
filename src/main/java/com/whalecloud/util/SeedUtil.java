package com.whalecloud.util;

import java.util.UUID;

/**
 * 生成序列化ID的工具包
 *
 * @author zhaoyanac
 * @date 2019/10/17
 */
public class SeedUtil {

    private static String lock = UUID.randomUUID().toString();
    private static Long curIndex = 0L;
    public static Long applactionServerNumber = 0L;
    private static Long startDate = 0L;

    public SeedUtil() {
    }

    public static Long getId() {
        Long index = null;
        synchronized(lock) {
            index = curIndex = curIndex + 1L % 1000L;
            if (curIndex >= 1000L) {
                curIndex = 0L;
            }
        }

        if (startDate == null || startDate <= 0L) {
            synchronized(lock) {
                try {
                    startDate = DateUtils.getDate("2016-01-01 00:00:00").getTime();
                } catch (Exception var4) {
                }
            }
        }

        return (System.currentTimeMillis() - startDate) * 100000L + index * 100L + applactionServerNumber;
    }

}


