package soba.weatherapp.ui.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SobaDeveloper on 6/4/16.
 */
public abstract class PermissionsActivity extends AppCompatActivity {

    private static final String TAG = PermissionsActivity.class.getSimpleName();
    protected static final int REQUEST_PERMISSIONS = 1;
    protected static List<String> permissionList;
    private static String[] PERMISSIONS_STRINGS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPermissions();
        initPermissionsIfNecessary();
    }

    protected abstract void addPermissions();

    private void initPermissionsIfNecessary() {
        Log.i(TAG, "initPermissionsIfNecessary");
        if (permissionList != null && permissionList.size() > 0) {
            Log.i(TAG, "initPermissionsIfNecessary, permissionList is NOT NULL");
            PERMISSIONS_STRINGS = new String[permissionList.size()];
            PERMISSIONS_STRINGS = permissionList.toArray(PERMISSIONS_STRINGS);
            checkPermissions();
        }
        else{
            startNextActivity();
        }
    }

    private void checkPermissions() {
        if (!isPermissionPermitted(permissionList)) {
            Log.i(TAG, "Permissions have NOT been granted. Requesting permissions.");
            requestPermissions();
        }
        else {
            startNextActivity();
        }
    }

    protected void requestPermissions() {
        if (shouldShowPermissionRationale(permissionList)) {
            //Prompt some dialog to show rationale
            ActivityCompat.requestPermissions(this, PERMISSIONS_STRINGS, REQUEST_PERMISSIONS);
        }
        else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STRINGS, REQUEST_PERMISSIONS);
        }
    }

    protected abstract void startNextActivity();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {
            Log.i(TAG, "Received response for Location permission request.");
            // We have requested multiple permissions for location, so all of them need to be checked
            if (verifyPermissions(grantResults)) {
                // All required permissions have been granted, start main activity.
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_LONG).show();
                startNextActivity();
            }
            else {
                Log.i(TAG, "Location permissions were NOT granted.");
                Toast.makeText(this, "Permission NOT granted", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * Source: https://github.com/googlesamples/android-RuntimePermissions/
     */

    private static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private boolean isPermissionPermitted(List<String> permissionList) {
        for (String permission : permissionList) {
            if (!(ContextCompat.checkSelfPermission(this, permission)
                    == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    private boolean shouldShowPermissionRationale(List<String> permissionList) {
        for (String permission : permissionList) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return false;
            }
        }
        return true;
    }

    protected void addPermission(String permission) {
        if (permissionList == null) {
            permissionList = new ArrayList<>();
        }
        permissionList.add(permission);
    }
}
