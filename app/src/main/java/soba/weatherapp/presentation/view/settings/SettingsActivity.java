package soba.weatherapp.presentation.view.settings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import soba.weatherapp.R;
import soba.weatherapp.WeatherApp;

/**
 * Created by SobaDeveloper on 5/26/16.
 */
public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ((WeatherApp) getApplication()).getMainComponent().inject(this);
        ButterKnife.bind(this);

        initUi();
    }

    private void initUi() {
        getFragmentManager().beginTransaction()
            .replace(R.id.container, new SettingsFragment())
            .commit();
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getString(R.string.action_settings));
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}