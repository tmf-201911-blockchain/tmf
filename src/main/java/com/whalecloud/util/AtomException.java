package com.whalecloud.util;

/**
 * @author zhaoyanac
 * @date 2019/10/14
 */
public class AtomException extends Exception {
    protected String className;
    protected String errorMessage;
    protected String methodName;
    protected Object[] args;
    protected Boolean canContinue = false;

    public AtomException() {
    }

    public AtomException(String className, String methodName, String errorMessage, Object... pArgs) {
        this.errorMessage = errorMessage;
        this.methodName = methodName;
        this.className = className;
        this.setArgs(pArgs);
    }

    @Override
    public String getMessage() {
        return this.errorMessage != null && !this.errorMessage.equals("") ? this.errorMessage : super.getMessage();
    }

    public String getFullMessage() {
        return this.errorMessage != null && !this.errorMessage.equals("") ? String.format(this.errorMessage, this.args) : super.getMessage();
    }

    @Override
    public String toString() {
        return String.format("AtomException:className=%s,methodName=%s,errorMessage=%s", this.className, this.methodName, this.errorMessage);
    }

    public Object[] getArgs() {
        return this.args;
    }

    public void setArgs(Object[] pArgs) {
        this.args = pArgs;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getCanContinue() {
        return this.canContinue;
    }

    public void setCanContinue(Boolean canContinue) {
        this.canContinue = canContinue;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}



