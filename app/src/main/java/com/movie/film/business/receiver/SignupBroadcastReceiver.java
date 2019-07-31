package com.movie.film.business.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.movie.film.business.MovieClient;

public class SignupBroadcastReceiver extends BroadcastReceiver {

    public static final String CLAZZ = SignupBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.v(CLAZZ,"Result : ["+action+"]");
    }
}
