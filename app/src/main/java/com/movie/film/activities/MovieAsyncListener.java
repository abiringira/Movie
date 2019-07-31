package com.movie.film.activities;

import com.movie.film.model.Results;


import java.util.ArrayList;

/**
 * Created by emmy on 24/05/2018.
 */

public interface  MovieAsyncListener {

    void onTaskStarted ();
    void onTaskCompleted(ArrayList<Results> data);
    void onTaskCompleted(Object data);
}
