package com.rainfool.kotlin.coroutines;

/**
 * Created by rainfool on 2019/5/14.
 */
public class GetUserProfile {

    public void execute() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    onResponse("Get User profile done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    onError("Get User profile error");
                }
            }
        }.start();
    }

    protected void onResponse(String json) {

    }

    protected void onError(String exception) {

    }
}
