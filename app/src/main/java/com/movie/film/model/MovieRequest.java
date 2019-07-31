package com.movie.film.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MovieRequest implements Serializable {

    private Results Movie;
    private String email;
    private String password;

    public MovieRequest() {

    }



    public Results getMovie() {
        return Movie;
    }

    public void setMovie(Results Movie) {
        this.Movie = Movie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
