package soba.weatherapp.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import soba.weatherapp.data.remote.repository.WeatherRepository;
import soba.weatherapp.data.remote.repository.WeatherRepositoryImpl;
import soba.weatherapp.data.remote.service.WeatherService;

/**
 * Created by Levi on 3/23/18.
 */

@Module public class RepositoryModule {

    @Provides @Singleton WeatherRepository providesWeatherRepository(WeatherService service) {
        return new WeatherRepositoryImpl(service);
    }
}
