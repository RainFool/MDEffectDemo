package com.rainfool.kotlin.coroutines.callback;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

/**
 * 网络回调统一 Callback ，默认在主线程回调
 *
 * @author rainfool
 * @date 2019/1/18
 */
public abstract class DataCallback<T> {

    Handler mHandler = new Handler(Looper.myLooper());

    /**
     * 网络成功回调
     *
     * @param rsp   网络实体，一般为JCE类
     * @param extra 调用方自己定义的其他数据类型
     */
    protected abstract void onResponse(T rsp, Object extra);

    /**
     * 网络回调失败
     *
     * @param callbackError 错误实体
     */
    protected abstract void onError(@NonNull CallbackError callbackError);

    public final void onResponseInner(final T rsp, final Object extra) {
        if (shouldDeliverOnMainThread() && !isCurrentOnMainThread()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onResponse(rsp, extra);
                }
            });
        } else {
            onResponse(rsp, extra);
        }
    }

    public final void onErrorInner(final int errorCode) {
        onErrorInner(errorCode, "", false);
    }

    public final void onErrorInner(final int errorCode, final String exception, final boolean fromCache) {
        onErrorInner(new CallbackError(errorCode, exception, fromCache));
    }

    public final void onErrorInner(@NonNull final CallbackError error) {
        if (shouldDeliverOnMainThread() && !isCurrentOnMainThread()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onError(error);
                }
            });
        } else {
            onError(error);
        }
    }

    public boolean shouldDeliverOnMainThread() {
        return true;
    }

    private boolean isCurrentOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
