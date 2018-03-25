package soba.weatherapp;

import android.app.Application;
import android.content.Context;
import soba.weatherapp.di.component.DaggerMainComponent;
import soba.weatherapp.di.component.MainComponent;
import soba.weatherapp.di.module.AppModule;

/**
 * Created by Levi on 5/15/16.
 */
public class WeatherApp extends Application {

    private static Context mContext;
    private MainComponent mainComponent;

    @Override public void onCreate() {
        super.onCreate();

        initDagger();
        mContext = this;
        // LeakCanary.install(this);
    }

    private void initDagger() {
        mainComponent = DaggerMainComponent.builder().appModule(new AppModule(this)).build();
    }

    public MainComponent getMainComponent(){
        return mainComponent;
    }

    //http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context/4391811#4391811
    public static Context getContext() {
        return mContext;
    }
}
