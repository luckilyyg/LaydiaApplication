package com.crazy.gy;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者：Administrator
 * 时间：2017/8/5
 * 功能：
 */
public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco类
        Fresco.initialize(this);
        app = this;
    }

    public static Context getAppContext() {
        return app;
    }

    public static Resources getAppResources() {
        return app.getResources();
    }
}
