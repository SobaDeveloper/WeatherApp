package soba.weatherapp.data.remote.repository;

import io.reactivex.Observable;
import soba.weatherapp.data.remote.service.WeatherService;
import soba.weatherapp.domain.models.forecastmodel.ForecastData;
import soba.weatherapp.domain.models.weathermodel.WeatherData;

/**
 * Created by Levi on 3/23/18.
 */

public class WeatherRepositoryImpl implements WeatherRepository {

    private final WeatherService weatherService;

    public WeatherRepositoryImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override public Observable<WeatherData> getWeatherByCity(String cityName, String units) {
        return weatherService.getWeatherByCity(cityName, units);
    }

    @Override public Observable<ForecastData> getForecastByCity(String cityName, String units) {
        return weatherService.getForecastByCity(cityName, units);
    }

    @Override
    public Observable<WeatherData> getWeatherByLocation(String lat, String lon, String units) {
        return weatherService.getWeatherByLocation(lat, lon, units);
    }

    @Override
    public Observable<ForecastData> getForecastByLocation(String lat, String lon, String units) {
        return weatherService.getForecastByLocation(lat, lon, units);
    }
}
