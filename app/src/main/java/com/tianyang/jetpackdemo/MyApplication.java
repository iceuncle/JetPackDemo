package com.tianyang.jetpackdemo;

import android.app.Application;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
