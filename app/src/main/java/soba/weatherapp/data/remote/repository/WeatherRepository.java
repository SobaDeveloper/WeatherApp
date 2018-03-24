package soba.weatherapp.data.remote.repository;

import io.reactivex.Observable;
import soba.weatherapp.domain.models.forecastmodel.ForecastData;
import soba.weatherapp.domain.models.weathermodel.WeatherData;

/**
 * Created by Levi on 3/23/18.
 */

public interface WeatherRepository {

    Observable<WeatherData> getWeatherByCity(String cityName, String units);

    Observable<ForecastData> getForecastByCity(String cityName, String units);

    Observable<WeatherData> getWeatherByLocation(String lat, String lon, String units);

    Observable<ForecastData> getForecastByLocation(String lat, String lon, String units);
}
