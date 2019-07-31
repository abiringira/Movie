package com.movie.film.business.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;

import com.movie.film.activities.MovieAsyncListener;
import com.movie.film.business.MovieClient;
import com.movie.film.business.MovieControllable;
import com.movie.film.model.MovieResult;
import com.movie.film.model.MovieResult;
import com.movie.film.model.MovieGenericError;

import java.util.concurrent.Callable;

@SuppressLint("NewApi")
public abstract class MovieBaseController extends AsyncTask<Object, Void, Object> implements MovieControllable<MovieResult>, Callable<MovieResult> {
    protected MovieAsyncListener MovieAsyncListener;
    protected Activity activity;
    protected Object request;
    protected MovieClient.MovieServiceType serviceType;

    public MovieBaseController(MovieAsyncListener asyncListener) {
        this.MovieAsyncListener = asyncListener;
    }


    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;

    }

    @Override
    public void setRequest(Object objectRequest) {
        this.request = objectRequest;

    }

    @Override
    public void setServiceType(MovieClient.MovieServiceType serviceType) {
        this.serviceType = serviceType;

    }

    @Override
    public MovieResult call() throws MovieGenericError {
        if (request != null && serviceType != null) {
            return manageService(serviceType, request);
        } else {
            MovieGenericError error = new MovieGenericError();
            error.setMessage("Error Missing Data");
            StringBuilder builder = new StringBuilder("");
            if (activity == null) {
                builder.append("Actitvity");
            }
            if (request == null) {
                builder.append("request");
            }
            if (serviceType == null) {
                builder.append("serviceType");
            }
            throw error;
        }

    }

    @Override
    public MovieResult manageServiceManyItem(MovieClient.MovieServiceType serviceType, Object requestData)  {
        MovieResult responseData = null;
        if (activity == null) {

        }
        try {
            this.request = requestData;
            responseData = workWithService();
        } catch (Throwable exc) {

            exc.getMessage();
        }
        return responseData;
    }

    @Override

    public MovieResult manageService(MovieClient.MovieServiceType serviceType, Object request) {
        MovieResult dataResponse = null;
        try {
            this.request = request;
            dataResponse = workWithService();
        } catch (Throwable exc) {
            MovieGenericError genericError = new MovieGenericError();
            genericError.setMessage(exc.getMessage());
        }


        return dataResponse;
    }

    abstract protected <T extends MovieResult> T workWithService();
}
