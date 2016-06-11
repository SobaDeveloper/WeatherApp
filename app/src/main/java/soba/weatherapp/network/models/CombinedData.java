package soba.weatherapp.network.models;

import soba.weatherapp.network.models.weathermodel.WeatherData;
import soba.weatherapp.network.models.forecastmodel.ForecastData;

/**
 * Created by Levi on 5/20/16.
 */
public class CombinedData {

    private WeatherData mWeatherData;
    private ForecastData mForecastData;

    public CombinedData(WeatherData weatherData, ForecastData forecastData) {
        mWeatherData = weatherData;
        mForecastData = forecastData;
    }

    public ForecastData getForecastData() {
        return mForecastData;
    }

    public WeatherData getWeatherData() {
        return mWeatherData;
    }
}
