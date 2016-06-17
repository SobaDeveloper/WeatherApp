package soba.weatherapp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import soba.weatherapp.R;
import soba.weatherapp.adapters.ViewPagerAdapter;
import soba.weatherapp.location.LocationProvider;
import soba.weatherapp.network.ApiClient;
import soba.weatherapp.network.models.CombinedData;
import soba.weatherapp.network.models.forecastmodel.ForecastData;
import soba.weatherapp.network.models.weathermodel.WeatherData;
import soba.weatherapp.ui.fragments.ForecastFragment;
import soba.weatherapp.ui.fragments.SettingsFragment;
import soba.weatherapp.ui.fragments.WeatherFragment;
import soba.weatherapp.utils.SharedPreferenceManager;

/**
 * Created by SobaDeveloper on 6/1/16
 */
public class MainActivity extends BaseActivity implements LocationProvider.CustomLocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private ViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private SharedPreferenceManager prefs;
    private LocationProvider mLocationProvider;
    private Location mLocation;
    private ProgressBar mProgressBar;
    private String mTempUnit = "";
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompositeSubscription = new CompositeSubscription();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        initUi();
        initPrefs();
    }

    private void initUi() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        if (mViewPager != null) {
            setupViewPager(mViewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setVisibility(View.INVISIBLE);
    }

    private void initPrefs() {
        prefs = SharedPreferenceManager.from(this);
        mTempUnit = prefs.getTempUnit();
        getLocationPrefs();
    }

    private void getLocationPrefs() {
        if (prefs.getLocationToggle()) {
            if (mLocationProvider == null) {
                mLocationProvider = new LocationProvider(this, this);
            }
        }
        else {
            getCombinedDataByCity();
        }
    }

    @Override
    public void onLocationFetched(Location location) {
        mLocation = new Location(location);
        getCombinedDataByLocation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Log.i(TAG, "User agreed to make required location settings changes.");
                    mLocationProvider.startLocationUpdates();
                    break;
                case Activity.RESULT_CANCELED:
                    Log.i(TAG, "User chose not to make required location settings changes.");
                    getCombinedDataByCity();
                    prefs.setLocationToggle(false);
                    break;
            }
        }
        else if (requestCode == SETTINGS_ACTION) {
            if (resultCode == SettingsFragment.PREFS_UPDATED) {
                Log.i(TAG, "User made changes to preferences");
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void getCombinedDataByLocation() {
        showProgressBar();
        Observable<CombinedData> combined = Observable.zip(getWeatherByLocation(),
                getForecastByLocation(), new Func2<WeatherData, ForecastData, CombinedData>() {
                    @Override
                    public CombinedData call(WeatherData weatherData, ForecastData forecastData) {
                        return new CombinedData(weatherData, forecastData);
                    }
                });

        mCompositeSubscription.add(combined
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CombinedData>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressBar();
                    }

                    @Override
                    public void onNext(CombinedData combinedData) {
                        updateFragments(combinedData);
                        mViewPager.setVisibility(View.VISIBLE);
                        hideProgressBar();
                    }
                }));
    }

    private void getCombinedDataByCity() {
        showProgressBar();
        if (prefs != null) {
            String selectedCity = prefs.getSelectedCity();
            Observable<CombinedData> combined2 = Observable.zip(getWeatherByCity(selectedCity),
                    getForecastByCity(selectedCity), new Func2<WeatherData, ForecastData, CombinedData>() {
                        @Override
                        public CombinedData call(WeatherData weatherData, ForecastData forecastData) {
                            return new CombinedData(weatherData, forecastData);
                        }
                    });

            mCompositeSubscription.add(combined2
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CombinedData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            hideProgressBar();
                        }

                        @Override
                        public void onNext(CombinedData combinedData) {
                            updateFragments(combinedData);
                            mViewPager.setVisibility(View.VISIBLE);
                            hideProgressBar();
                        }
                    }));
        }
    }

    private Observable<WeatherData> getWeatherByLocation() {
        return ApiClient.getApi()
                .getWeatherByLocation(String.valueOf(mLocation.getLatitude()), String.valueOf(mLocation.getLongitude()),
                        mTempUnit);
    }

    private Observable<ForecastData> getForecastByLocation() {
        return ApiClient.getApi()
                .getForecastByLocation(String.valueOf(mLocation.getLatitude()), String.valueOf(mLocation.getLongitude()),
                        mTempUnit);
    }

    private Observable<WeatherData> getWeatherByCity(String selectedCity) {
        return ApiClient.getApi().getWeatherByCity(selectedCity, mTempUnit);
    }

    private Observable<ForecastData> getForecastByCity(String selectedCity) {
        return ApiClient.getApi().getForecastByCity(selectedCity, mTempUnit);
    }

    private void updateFragments(CombinedData combinedData) {
        ((WeatherFragment) mAdapter.getRegisteredFragment(0))
                .updateData(combinedData.getWeatherData());
        ((ForecastFragment) mAdapter.getRegisteredFragment(1))
                .updateData(combinedData.getForecastData());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLocationProvider != null) {
            mLocationProvider.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocationProvider != null) {
            mLocationProvider.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mLocationProvider != null) {
            if (mLocationProvider.isConnected()) {
                mLocationProvider.stopLocationUpdates();
            }
        }
    }

    @Override
    protected void onDestroy() {
        mCompositeSubscription.unsubscribe();
        super.onDestroy();
    }

    private void showProgressBar() {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}