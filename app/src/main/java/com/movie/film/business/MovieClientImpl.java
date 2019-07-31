package com.movie.film.business;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class MovieClientImpl extends Service implements MovieClient {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean isDeviceConnected() {
        Boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            isConnected = true;
        }
        return isConnected;
    }


    public static MovieServiceType getServiceType (String processingService) {
        MovieServiceType serviceType = null;


        return serviceType;
    }

}
