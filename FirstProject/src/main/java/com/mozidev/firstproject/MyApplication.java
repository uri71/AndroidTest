package com.mozidev.firstproject;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by y.storchak on 1/24/14.
 */
public class MyApplication extends Application{

    public void onCreate(){
        super.onCreate();
    }
    public void onLowMemory(){
        super.onLowMemory();
    }
    public void onTerminate(){
        super.onTerminate();
    }
    public  void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
}
