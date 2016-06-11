package soba.weatherapp;

import android.app.Application;
import android.content.Context;

import soba.weatherapp.network.ApiClient;

/**
 * Created by Levi on 5/15/16.
 */
public class WeatherApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.init();
//        LeakCanary.install(this);
        mContext = this;
    }

    //http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context/4391811#4391811
    public static Context getContext(){
        return mContext;
    }
}
