package com.rainfool.kotlin.coroutines.callback;

public class CallbackError {
    private final int errorCode;
    private final String exception;
    private final boolean fromCache;

    /**
     * @param errorCode 错误码
     * @param exception 错误描述
     * @param fromCache 是否来自cache
     */
    public CallbackError(int errorCode, String exception, boolean fromCache) {
        this.errorCode = errorCode;
        this.exception = exception;
        this.fromCache = fromCache;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getException() {
        return exception;
    }

    public boolean isFromCache() {
        return fromCache;
    }

    @Override
    public String toString() {
        return "CallbackError{" +
                "errorCode=" + errorCode +
                ", exception='" + exception + '\'' +
                ", fromCache=" + fromCache +
                '}';
    }
}
