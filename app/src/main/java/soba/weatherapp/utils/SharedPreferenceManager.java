package soba.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by SobaDeveloper on 6/5/16.
 */
public class SharedPreferenceManager {

    private final static String TAG = SharedPreferenceManager.class.getSimpleName();
    private SharedPreferences prefs;

    private SharedPreferenceManager(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceManager from(Context context) {
        return new SharedPreferenceManager(context);
    }

    public void registerChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void setLocationToggle(boolean locationToggle){
        prefs.edit().putBoolean(Constants.PREF_LOCATION_TOGGLE, locationToggle).apply();
    }

    public boolean getLocationToggle() {
        return prefs.getBoolean(Constants.PREF_LOCATION_TOGGLE, true);
    }

    public String getCity() {
        return prefs.getString(Constants.PREF_CITY, Constants.PREF_CITY_DEFAULT);
    }

    public void setCity(String city) {
        prefs.edit().putString(Constants.PREF_CITY, city).apply();
    }

    public String getCountry() {
        return prefs.getString(Constants.PREF_COUNTRY, Constants.PREF_COUNTRY_DEFAULT);
    }

    public void setCountry(String country) {
        prefs.edit().putString(Constants.PREF_COUNTRY, country).apply();
    }

    public boolean isUnitMetric(){
        return getUnit().equalsIgnoreCase(Constants.PREF_UNIT_METRIC);
    }
    public String getUnit() {
        return prefs.getString(Constants.PREF_UNIT, Constants.PREF_UNIT_METRIC);
    }

    public void setUnit(String unit) {
        prefs.edit().putString(Constants.PREF_UNIT, unit).apply();
    }

    public String getSelectedCity(){
        return getCity() + "," + WeatherUtil.getCountry(getCountry());
    }

    public String getTempUnit(){
        return isUnitMetric() ? Constants.API_METRIC : Constants.API_IMPERIAL;
    }
}
