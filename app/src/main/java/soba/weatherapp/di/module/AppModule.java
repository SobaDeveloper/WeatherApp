package soba.weatherapp.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import soba.weatherapp.WeatherApp;

/**
 * Created by Levi on 3/23/18.
 */

@Module public class AppModule {

    private WeatherApp application;

    public AppModule(WeatherApp application) {
        this.application = application;
    }

    @Provides @Singleton WeatherApp providesApplication() {
        return this.application;
    }
}
