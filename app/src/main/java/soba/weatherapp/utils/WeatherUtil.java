package soba.weatherapp.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import soba.weatherapp.R;
import soba.weatherapp.WeatherApp;

/**
 * Created by SobaDeveloper on 5/31/16.
 */
public final class WeatherUtil {

    private static Context mContext = WeatherApp.getContext();

    private WeatherUtil() {
    }

    /**
     * Get day of the week for the forecast display
     */
    public static String getWeekDay(Integer timestamp) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp * 1000L);
        TimeZone timeZone = TimeZone.getDefault();
        c.add(Calendar.MILLISECOND, timeZone.getOffset(c.getTimeInMillis()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        return dateFormat.format(c.getTime());
    }

    public static String getDate(Integer timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
        return dateFormat.format(c.getTime());
    }

    /**
     * Get formatted local time for the sunrise/sunset
     */
    public static String getTime(Integer unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    /**
     * Get String of temperature from double
     */
    public static String getTempString(Double tempDouble, boolean isMetric) {
        if (tempDouble != null) {
            int tempInt = (int) Math.round(tempDouble);
            if (isMetric) {
                return String.valueOf(tempInt) + getUnits(true);
            }
            else {
                return String.valueOf(tempInt) + getUnits(false);
            }
        }
        return null;
    }

    private static String getUnits(boolean isMetric) {
        return isMetric ? mContext.getString(R.string.weather_unit_metric) :
                mContext.getString(R.string.weather_unit_imp);
    }

    /**
     * Get ISO country code for use in API call
     */
    public static String getCountry(String countryName) {
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale locale = new Locale("en", iso);
            countries.put(locale.getDisplayCountry(), iso);
        }
        return countries.get(countryName);
    }

    /**
     * Get weather icon based on weather id
     */
    public static String getWeatherIcon(int weatherId) {

        int id = weatherId / 100;
        String iconText = "";

        if (id == 8) {
            iconText = mContext.getString(R.string.weather_sunny);
        }

        else {
            switch (id) {
                case 2:
                    iconText = mContext.getString(R.string.weather_thunder);
                    break;
                case 3:
                    iconText = mContext.getString(R.string.weather_drizzle);
                    break;
                case 5:
                    iconText = mContext.getString(R.string.weather_rainy);
                    break;
                case 6:
                    iconText = mContext.getString(R.string.weather_snowy);
                    break;
                case 7:
                    iconText = mContext.getString(R.string.weather_foggy);
                    break;
            }
        }
        return iconText;
    }

    public static String getHumidityIcon() {
        return mContext.getString(R.string.weather_humidity);
    }

    public static String getPressureIcon() {
        return mContext.getString(R.string.weather_barometer);
    }

    public static String getWindIcon() {
        return mContext.getString(R.string.weather_wind);
    }

    public static String getSunriseIcon() {
        return mContext.getString(R.string.weather_sunrise);
    }

    public static String getSunsetIcon() {
        return mContext.getString(R.string.weather_sunset);
    }
}