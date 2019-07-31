package com.movie.film.business.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;

import com.movie.film.activities.MovieAsyncListenerManyItem;
import com.movie.film.business.MovieClient;
import com.movie.film.business.MovieControllable;
import com.movie.film.model.MovieResult;
import com.movie.film.model.MovieData;
import com.movie.film.model.MovieGenericError;

import java.util.concurrent.Callable;

@SuppressLint("NewApi")
public abstract class MovieBaseControllerManyItem extends AsyncTask<Object, Void, MovieResult> implements MovieControllable<MovieResult>, Callable<MovieResult>{
    private final static String CLAZZ = MovieBaseControllerManyItem.class.getName();
    protected MovieClient.MovieServiceType serviceType;
    protected Object request;
    protected Activity activity = new Activity();
    protected MovieAsyncListenerManyItem asyncListener;

    public MovieBaseControllerManyItem(MovieAsyncListenerManyItem asyncListener){
        this.asyncListener = asyncListener;
    }

    @Override
    public final synchronized void setServiceType(MovieClient.MovieServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public final synchronized void setRequest(Object request) {
        this.request = request;
    }

    @Override
    public MovieResult manageService(MovieClient.MovieServiceType serviceType, Object request) {
        return null;
    }

    /**
     * Used set reference to the activity that is requesting data
     * @param activity
     *              screen where the request comes from
     */
    @Override
    public final synchronized void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MovieResult call() throws MovieGenericError {
        if(serviceType != null && request != null) {
            return manageServiceManyItem(serviceType, request);
        }
        else {
            MovieGenericError genericError = new MovieGenericError();
            genericError.setMessage("Missing Input data");
            StringBuilder field = new StringBuilder("");
            if (activity == null) {
                field.append("activity, ");
            }
            if (serviceType == null) {
                field.append("serviceType, ");
            }
            if (request == null) {
                field.append("request");
            }
            throw genericError;
        }
    }


    @Override
    public MovieResult manageServiceManyItem(MovieClient.MovieServiceType serviceType, Object requestData)  {
        MovieResult responseData = null;
        try {
            this.request = requestData;
            responseData = workWithService();
        } catch (Throwable exc) {

            exc.getMessage();
        }
        return responseData;
    }


    abstract protected MovieResult workWithService();

    @Override
    protected MovieResult doInBackground(Object... objects) {
        return null;
    }
}
