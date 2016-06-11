package soba.weatherapp.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import soba.weatherapp.network.models.weathermodel.WeatherData;
import soba.weatherapp.network.models.forecastmodel.ForecastData;
import soba.weatherapp.utils.Constants;

/**
 * Created by Levi on 5/15/16.
 */
public interface ApiInterface {

    @GET(Constants.API_WEATHER)
    Observable<WeatherData> getWeatherByCity(@Query("q") String cityName,
                                             @Query("units") String units,
                                             @Query("appid") String appId);

    @GET(Constants.API_FORECAST)
    Observable<ForecastData> getForecastByCity(@Query("q") String cityName,
                                               @Query("cnt") int count,
                                               @Query("units") String units,
                                               @Query("appid") String appId);

    @GET(Constants.API_WEATHER)
    Observable<WeatherData> getWeatherByLocation(@Query("lat") String lat,
                                                 @Query("lon") String lon,
                                                 @Query("units") String units,
                                                 @Query("appid") String appId);

    @GET(Constants.API_FORECAST)
    Observable<ForecastData> getForecastByLocation(@Query("lat") String lat,
                                                   @Query("lon") String lon,
                                                   @Query("cnt") int count,
                                                   @Query("units") String units,
                                                   @Query("appid") String appId);
}
