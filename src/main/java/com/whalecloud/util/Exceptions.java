package com.whalecloud.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zhaoyanac
 * @date 2019/10/14
 */
public class Exceptions {
    public static String c_ArgsNoEnough = "参数不全";
    public static String c_ConcurrencyOperate = "并发操作异常";
    public static String c_SystemError = "系统执行异常！";
    public static String c_PermissionDenied = "无权限";

    public Exceptions() {
    }

    public static RuntimeException unchecked(Exception e) {
        return e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException(e);
    }

    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static boolean isCausedBy(Exception ex, Class... causeExceptionClasses) {
        for(Object cause = ex; cause != null; cause = ((Throwable)cause).getCause()) {
            Class[] var3 = causeExceptionClasses;
            int var4 = causeExceptionClasses.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Class<? extends Exception> causeClass = var3[var5];
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static AtomException atomException(String errMessage, Object... args) {
        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[2];
        String className = traceElement.getClassName();
        String methodName = traceElement.getMethodName();
        int lineNumber = traceElement.getLineNumber();
        return new AtomException(className, methodName, errMessage, args);
    }

}



