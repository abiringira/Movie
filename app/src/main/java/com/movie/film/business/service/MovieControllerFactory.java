package com.movie.film.business.service;

import com.movie.film.activities.MovieAsyncListener;
import com.movie.film.activities.MovieAsyncListenerManyItem;

import java.io.IOException;

public class MovieControllerFactory {

    public enum MovieControllerType {
        PING_SERVER,
        LOGIN,
        CREATE_EXPENSE,
        RESET_PASSWORD,
        USER_ACTIVITIES
    }

    public static MovieBaseController createController(MovieControllerType controllerType, MovieAsyncListener activity) {
        MovieBaseController controller = null;
        switch (controllerType) {

            case PING_SERVER:
                controller = new MoviePingController(activity);
                break;
            case CREATE_EXPENSE:
                controller = new MovieBookController(activity);
                break;
            case RESET_PASSWORD:
                controller = new MovieBookController(activity);
                break;
        }

        return controller;
    }

    public static MovieBaseControllerManyItem createControllerManyItem(MovieControllerType controllerType, MovieAsyncListenerManyItem activity) {
        MovieBaseControllerManyItem controller = null;
        switch (controllerType) {
            case USER_ACTIVITIES:
                controller = new MovieUserActivitiesController(activity);
                break;
        }

        return controller;
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}


