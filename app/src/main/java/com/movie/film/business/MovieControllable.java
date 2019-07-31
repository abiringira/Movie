package com.movie.film.business;

import android.app.Activity;

import com.movie.film.model.MovieResult;

import com.movie.film.model.MovieData;


public interface MovieControllable<T extends MovieResult> {
    T manageService(MovieClient.MovieServiceType serviceType,Object request);
    void setActivity(Activity activity);
    public void setRequest(Object objectRequest);
    public void setServiceType(MovieClient.MovieServiceType serviceType);
    MovieResult manageServiceManyItem(MovieClient.MovieServiceType serviceType, Object requestData);

}
