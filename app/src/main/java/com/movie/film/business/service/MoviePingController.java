package com.movie.film.business.service;

import android.app.Application;
import android.util.Log;

import com.movie.film.activities.MovieAsyncListener;
import com.movie.film.business.MovieClient;
import com.movie.film.model.MovieGenericError;
import com.movie.film.model.MoviePingResult;
import com.movie.film.model.MovieResult;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class MoviePingController extends MovieBaseController {
    private final RestTemplate restTemplate = new RestTemplate();

    public MoviePingController(MovieAsyncListener asyncListener) {
        super(asyncListener);
    }

    @Override
    protected void onPreExecute() {
        MovieAsyncListener.onTaskStarted();
    }

    @Override
    protected MovieResult workWithService() {
        MoviePingResult pingResult = null;
        if (this.request != null) {
            try {
                String request = "{}";
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity entity = new HttpEntity(request, headers);
                ResponseEntity<MoviePingResult> response = restTemplate.postForEntity(MovieClient.PING_SERVER_RELATIVE_PATH, entity, MoviePingResult.class);
                HttpStatus status = response.getStatusCode();
                if (HttpStatus.OK == status) {
                    pingResult = response.getBody();

                }
            } catch (HttpClientErrorException exc) {
                Log.e("ResultCode:", String.valueOf(exc.getStatusCode().value()));
                Log.e("WorkWith Service", exc.getResponseBodyAsString());

            }


        } else {
            Log.e("Error", "Request is Null");

        }

        return pingResult;
    }

    @Override
    protected MoviePingResult doInBackground(Object... objects) {
        MoviePingResult result = null;
        try {
            if (objects != null && objects.length > 0) {
                Object data = objects[0];
                if (data instanceof MoviePingResult) {
                    result = (MoviePingResult) this.manageService(MovieClient.MovieServiceType.PING, (MoviePingResult) data);
                }
            }
        } catch (Throwable error) {
            Log.e("Ping", error.getMessage());
        }
        return result;
    }

    @Override
    protected void onPostExecute(Object data) {
        if (data != null) {
            MovieAsyncListener.onTaskCompleted((MoviePingResult) data);
        } else {
            Log.e("MoviePingResult", "unable process Request");

        }

    }
}
