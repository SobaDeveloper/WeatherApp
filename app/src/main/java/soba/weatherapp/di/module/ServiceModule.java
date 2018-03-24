package soba.weatherapp.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import soba.weatherapp.data.remote.service.WeatherService;

/**
 * Created by Levi on 3/23/18.
 */

@Module public class ServiceModule {

    @Provides @Singleton WeatherService providesWeatherServices(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }
}
