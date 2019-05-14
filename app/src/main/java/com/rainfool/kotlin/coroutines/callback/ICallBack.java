package com.rainfool.kotlin.coroutines.callback;

/**
 * @author lijunqing on 2018/11/21
 */

public interface ICallBack {

    abstract class ImgMonitorCallBack implements ICallBack {
        public abstract void onReqSuccess(long cost, String url);

        public abstract void onReqFail(long cost, String url, int rectCode);
    }
}
