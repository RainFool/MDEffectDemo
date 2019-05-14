package com.rainfool.kotlin.coroutines;

import com.rainfool.kotlin.coroutines.callback.DataCallback;

/**
 * Created by rainfool on 2019/5/14.
 */
public class UserSerivece {


    private UserSerivece() {
    }

    public static UserSerivece getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserSerivece INSTANCE = new UserSerivece();
    }

    public void getUserProfile(final DataCallback<String> callback) {
        new GetUserProfile() {
            @Override
            protected void onResponse(String json) {
                super.onResponse(json);
                callback.onResponseInner(json, false);
            }

            @Override
            protected void onError(String exception) {
                super.onError(exception);
                callback.onErrorInner(0);
            }
        }.execute();
    }
}
