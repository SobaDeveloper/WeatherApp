package soba.weatherapp.utils;

import android.content.Context;

import soba.weatherapp.R;
import soba.weatherapp.WeatherApp;

/**
 * Created by SobaDeveloper on 5/15/16.
 */
public final class Constants {

    private static Context mContext = WeatherApp.getContext();

    public static final String API_KEY = "523dcb17e6b92527d1ed9df5d4533d46";
    public static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String API_WEATHER = "weather";
    public static final String API_FORECAST = "forecast/daily";
    public static final String API_IMPERIAL = "imperial";
    public static final String API_METRIC = "metric";

    public static final String TAB_CURRENT = mContext.getString(R.string.tab_current);
    public static final String TAB_FORECAST = mContext.getString(R.string.tab_forecast);

    public static final String PREF_LOCATION_TOGGLE = "key_location_toggle";
    public static final String PREF_CITY = "key_city";
    public static final String PREF_COUNTRY = "key_country";
    public static final String PREF_UNIT = "key_unit";
    public static final String PREF_UNIT_METRIC = "c";

    public static final String PREF_CITY_SUMMARY_NULL = mContext.getString(R.string.pref_summary_city_null);
    public static final String PREF_CITY_DEFAULT = mContext.getString(R.string.pref_default_city);
    public static final String PREF_COUNTRY_DEFAULT = mContext.getString(R.string.pref_default_country);
    public static final String PREF_UNIT_SUMMARY = mContext.getString(R.string.pref_temperature_summary);

    public static final String DETAIL_LABEL_HUMIDITY = "%";
    public static final String DETAIL_LABEL_PRESSURE = " hPa";
    public static final String DETAIL_LABEL_WIND_METRIC = " m/s";
    public static final String DETAIL_LABEL_WIND_IMP = " mph";

}
