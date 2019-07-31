package com.movie.film.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
 public class MovieResult implements Serializable {

    private ArrayList<Results> Results;

    public ArrayList<com.movie.film.model.Results> getResults() {
        return Results;
    }

    public void setResults(ArrayList<com.movie.film.model.Results> results) {
        Results = results;
    }
}
