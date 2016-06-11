package soba.weatherapp.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by SobaDeveloper on 6/1/16.
 *
 */
public class SplashActivity extends PermissionsActivity {

    private static int SPLASH_TIMER = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void addPermissions() {
        addPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        addPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    protected void startNextActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
