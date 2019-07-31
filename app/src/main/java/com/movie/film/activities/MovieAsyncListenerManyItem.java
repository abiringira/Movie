package com.movie.film.activities;

import com.movie.film.model.Results;
import com.movie.film.model.MovieData;

import java.util.ArrayList;

/**
 * Created by emmy on 24/05/2018.
 */

public interface MovieAsyncListenerManyItem extends MovieAsyncListener {

    void onTaskCompleted(ArrayList<Results> data);
    void onTaskCompleted(MovieData data);
}
