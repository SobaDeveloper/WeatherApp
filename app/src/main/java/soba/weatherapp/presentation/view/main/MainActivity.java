package soba.weatherapp.presentation.view.main;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import soba.weatherapp.R;
import soba.weatherapp.presentation.adapters.ViewPagerAdapter;
import soba.weatherapp.data.remote.repository.WeatherRepository;
import soba.weatherapp.domain.schedulers.SchedulerProvider;
import soba.weatherapp.utils.location.LocationProvider;
import soba.weatherapp.domain.models.CombinedData;
import soba.weatherapp.presentation.common.BaseActivity;
import soba.weatherapp.presentation.view.settings.SettingsFragment;
import soba.weatherapp.utils.SharedPreferenceManager;

/**
 * Created by SobaDeveloper on 6/1/16
 */
public class MainActivity extends BaseActivity
    implements LocationProvider.CustomLocationListener, MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private ViewPagerAdapter mAdapter;
    private SharedPreferenceManager prefs;
    private LocationProvider mLocationProvider;
    private Location mLocation;
    private String mTempUnit = "";

    @BindView(R.id.viewpager) ViewPager mViewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Inject SchedulerProvider schedulerProvider;
    @Inject WeatherRepository weatherRepository;
    private MainContract.Presenter mainPresenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMainComponent().inject(this);
        ButterKnife.bind(this);

        initUi();

        initPresenter();

        initPrefs();
    }

    private void initUi() {
        tabLayout.setupWithViewPager(mViewPager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setVisibility(View.INVISIBLE);
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this, weatherRepository, schedulerProvider);
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
        } else {
            getCombinedDataByCity();
        }
    }

    @Override public void onLocationFetched(Location location) {
        mLocation = new Location(location);
        getCombinedDataByLocation();
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        } else if (requestCode == SETTINGS_ACTION) {
            if (resultCode == SettingsFragment.PREFS_UPDATED) {
                Log.i(TAG, "User made changes to preferences");
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void getCombinedDataByCity() {
        if (prefs != null) {
            showProgressBar();
            String selectedCity = prefs.getSelectedCity();
            mainPresenter.getDataByCity(selectedCity, mTempUnit);
        }
    }

    @Override public void onDataByCityRetrieved(CombinedData data) {
        updateFragments(data);
        mViewPager.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    @Override public void onDataByCityFailed() {
        hideProgressBar();
    }

    private void getCombinedDataByLocation() {
        showProgressBar();
        mainPresenter.getDataByLocation(String.valueOf(mLocation.getLatitude()),
            String.valueOf(mLocation.getLongitude()), mTempUnit);
    }

    @Override public void onDataByLocationRetrieved(CombinedData data) {
        updateFragments(data);
        mViewPager.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    @Override public void onDataByLocationFailed() {
        hideProgressBar();
    }

    private void updateFragments(CombinedData combinedData) {
        ((WeatherFragment) mAdapter.getRegisteredFragment(0)).updateData(
            combinedData.getWeatherData());
        ((ForecastFragment) mAdapter.getRegisteredFragment(1)).updateData(
            combinedData.getForecastData());
    }

    @Override protected void onResume() {
        super.onResume();
        if (mLocationProvider != null) {
            mLocationProvider.connect();
        }
    }

    @Override protected void onStop() {
        super.onStop();
        if (mLocationProvider != null) {
            mLocationProvider.disconnect();
        }
    }

    @Override protected void onPause() {
        super.onPause();
        if (mLocationProvider != null) {
            if (mLocationProvider.isConnected()) {
                mLocationProvider.stopLocationUpdates();
            }
        }
    }

    @Override protected void onDestroy() {
        mainPresenter.unsubscribe();
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