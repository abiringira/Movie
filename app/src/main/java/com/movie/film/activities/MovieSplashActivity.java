package com.movie.film.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.movie.film.business.MovieClient;
import com.movie.film.business.service.MovieControllerFactory;
import com.movie.film.business.service.MoviePingController;
import com.movie.film.model.MovieData;
import com.movie.film.model.MoviePingResult;


public class MovieSplashActivity extends MovieBaseActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity_splash);
        if (!isNetworkAvailable()) {
            Log.e("cONNETING", "Error Connecting to the server");
            return;

        } else {
            /*MoviePingResult pingResult = new MoviePingResult();
            serviceType = MovieClient.MovieServiceType.PING;
            MoviePingController controller = (MoviePingController) MovieControllerFactory.createController(MovieControllerFactory.MovieControllerType.PING_SERVER, this);
            if (controller != null) {
                controller.setActivity(this);
                controller.setServiceType(MovieClient.MovieServiceType.PING);
                controller.execute(pingResult);*/
            startTheApp();;
            //}


        }
    }

    public void startTheApp() {
        Intent intent = new Intent(this, MovieMainActivity.class);
        startActivity(intent);
        finish();
    }

   /* @Override
    public void onTaskStarted() {
        imageView = findViewById(R.id.splash_image);
        if (serviceType == MovieClient.MovieServiceType.PING) {
            imageView.setVisibility(View.VISIBLE);

        } else {
            imageView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onTaskCompleted(MovieData data) {
        if (data != null && this.serviceType == MovieClient.MovieServiceType.PING && data instanceof MoviePingResult) {
            pingResult = (MoviePingResult) data;
            if (pingResult.getErrorMessage() != null) {
                Log.e("ErrorOnTASKCompleted", "Ping server Error");
            } else {
                startTheApp();
            }
        } else {
            Log.e("OnTaskCompleted", "Error pinging server");
        }
    }*/
}
