package soba.weatherapp.presentation.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import soba.weatherapp.R;
import soba.weatherapp.WeatherApp;
import soba.weatherapp.di.component.MainComponent;
import soba.weatherapp.presentation.view.settings.SettingsActivity;

/**
 * Created by SobaDeveloper on 5/16/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected final static int SETTINGS_ACTION = 99;

    private WeatherApp getWeatherApp() {
        return (WeatherApp) getApplication();
    }

    protected MainComponent getMainComponent() {
        return getWeatherApp().getMainComponent();
    }

    @Override public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, SETTINGS_ACTION);
        }
        return super.onOptionsItemSelected(item);
    }
}
