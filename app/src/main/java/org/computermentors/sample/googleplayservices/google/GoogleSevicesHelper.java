package org.computermentors.sample.googleplayservices.google;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

public class GoogleSevicesHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    public interface GoogleServicesListener{
        public void onConnected();
        public void onDisconnected();
    }

    public static final int REQUEST_CODE_RESOLUTION = -100;
    public static final int REQUEST_CODE_AVAILABILITY = -101;

    private Activity activity;
    private GoogleServicesListener listener;
    private GoogleApiClient apiClient;

    public GoogleSevicesHelper(Activity activity, GoogleServicesListener listener){
        this.listener = listener;
        this.activity = activity;

        this.apiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void connect(){
        apiClient.connect();
    }

    public void disconnect(){
        apiClient.disconnect();
    }

    private boolean isGooglePlayServicesAvailable(){
        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity);
        switch (availability){
            case ConnectionResult.SUCCESS:
                return true;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
            case ConnectionResult.SERVICE_DISABLED:
            case ConnectionResult.SERVICE_INVALID:
                GoogleApiAvailability.getInstance().getErrorDialog(activity, availability,REQUEST_CODE_AVAILABILITY).show();
                return false;
            default:
                return false;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
