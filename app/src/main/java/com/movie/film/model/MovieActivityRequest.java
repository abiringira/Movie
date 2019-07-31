package com.movie.film.model;

public class MovieActivityRequest extends MovieRequest {
    private String [] activityTypes;

    public String[] getActivityTypes() {
        return activityTypes;
    }

    public void setActivityTypes(String[] activityTypes) {
        this.activityTypes = activityTypes;
    }

}
