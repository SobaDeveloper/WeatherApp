package soba.weatherapp.presentation.view.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import android.util.Log;

import soba.weatherapp.R;
import soba.weatherapp.utils.Constants;
import soba.weatherapp.utils.SharedPreferenceManager;

/**
 * Created by SobaDeveloper on 5/26/16.
 *
 */
public class SettingsFragment extends PreferenceFragment {

    private final static String TAG = SettingsFragment.class.getSimpleName();
    public final static int PREFS_UPDATED = 1;
    private Preference prefCity;
    private Preference prefCountry;
    private Preference prefTemp;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    private SharedPreferenceManager prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        prefs = SharedPreferenceManager.from(getActivity());
        prefCity = getPreferenceScreen().findPreference(Constants.PREF_CITY);
        prefCountry = getPreferenceScreen().findPreference(Constants.PREF_COUNTRY);
        prefTemp = getPreferenceScreen().findPreference(Constants.PREF_UNIT);

        setCitySummary();
        setCountrySummary();
        setTempSummary();
        initPrefListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        prefs.registerChangeListener(prefListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.unregisterChangeListener(prefListener);
    }

    private void setCitySummary() {
        String city = prefs.getCity();
        if (TextUtils.isEmpty(city)) {
            prefCity.setSummary(Constants.PREF_CITY_SUMMARY_NULL);
        }
        else {
            prefCity.setSummary(city);
        }
    }

    private void setCountrySummary() {
        prefCountry.setSummary(prefs.getCountry());
    }

    private void setTempSummary() {
        String unit = prefs.getUnit() != null ? "\u00B0"
                + prefs.getUnit().toUpperCase() : "";
        prefTemp.setSummary(Constants.PREF_UNIT_SUMMARY + " " + unit);
    }

    private void initPrefListener() {
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.i(TAG, "*** Preference has been changed");
                getActivity().setResult(PREFS_UPDATED);
                switch (key) {
                    case Constants.PREF_CITY:
                        setCitySummary();
                        break;

                    case Constants.PREF_COUNTRY:
                        setCountrySummary();
                        break;

                    case Constants.PREF_UNIT:
                        setTempSummary();
                        break;
                }
            }
        };
    }
}
