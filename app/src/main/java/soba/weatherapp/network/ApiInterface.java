package soba.weatherapp.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import soba.weatherapp.network.models.forecastmodel.ForecastData;
import soba.weatherapp.network.models.weathermodel.WeatherData;
import soba.weatherapp.utils.Constants;

/**
 * Created by Levi on 5/15/16.
 */
public interface ApiInterface {

    @GET(Constants.API_WEATHER + Constants.API_APP_ID) Observable<WeatherData> getWeatherByCity(
        @Query("q") String cityName, @Query("units") String units);

    @GET(Constants.API_FORECAST + Constants.API_APP_ID) Observable<ForecastData> getForecastByCity(
        @Query("q") String cityName, @Query("units") String units);

    @GET(Constants.API_WEATHER + Constants.API_APP_ID) Observable<WeatherData> getWeatherByLocation(
        @Query("lat") String lat, @Query("lon") String lon, @Query("units") String units);

    @GET(Constants.API_FORECAST + Constants.API_APP_ID)
    Observable<ForecastData> getForecastByLocation(@Query("lat") String lat,
        @Query("lon") String lon, @Query("units") String units);
}
