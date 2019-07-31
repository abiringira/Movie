package com.movie.film.business.service;

import android.util.Log;
import com.movie.film.activities.MovieAsyncListener;
import com.movie.film.business.MovieClient;
import com.movie.film.model.Results;
import com.movie.film.model.MovieResult;
import com.movie.film.model.MovieRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class MovieBookController extends MovieBaseController {

    private final RestTemplate restTemplate = new RestTemplate();

    public MovieBookController(MovieAsyncListener asyncListener) {
        super(asyncListener);
    }

    @Override
    protected MovieResult workWithService() {
        MovieResult dataResponse = null;
        if (this.request != null) {
            try {
                //List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
                //messageConverters.add(new MappingJackson2HttpMessageConverter());
                //restTemplate.setMessageConverters(messageConverters);
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                MovieRequest expenseRequestData = (MovieRequest) request;
                HttpEntity<MovieRequest> entity = new HttpEntity<MovieRequest>(expenseRequestData, headers);
                ResponseEntity<MovieResult> response = restTemplate.postForEntity(MovieClient.MOVIE_DETAILS_PATH, entity, MovieResult.class);
                HttpStatus status = response.getStatusCode();
                if (HttpStatus.OK == status) {
                    dataResponse = response.getBody();
                } else {
                    Log.e("Status code equal :", status.toString());
                }

            } catch (HttpClientErrorException exc) {
                Log.e("Status:", String.valueOf(exc.getStatusCode().value()));
                Log.e("Catching the exception", exc.getResponseBodyAsString());

            }
        } else {
            Log.e("Result of request", "The request is null");

        }

        return dataResponse;
    }

    @Override
    protected MovieResult doInBackground(Object... data) {
        MovieResult expenseData = null;
        try {
            if (data != null && data.length > 0) {
                Object dataElement = data[0];
                //Log.e("MAPPING iF", lst.toString());
                if (dataElement instanceof MovieRequest) {
                    Log.e("MAPPING", "FINDING BUG");
                    expenseData = this.manageService(MovieClient.MovieServiceType.MOVIE_DETAILS, dataElement);

                }
            }
        } catch (Throwable exc) {
            Log.e("Do In", exc.getMessage());
        }

        return expenseData;
    }


    @Override
    protected void onPreExecute() {
        MovieAsyncListener.onTaskStarted();
    }

    @Override
    protected void onPostExecute(Object data) {
        if (data != null) {
            if (data instanceof Results) {
                MovieAsyncListener.onTaskCompleted(data);
                Log.e("DATAObject", "Data oject == null");
            }
        } else {
            Log.e("Error", "onPostExecute");
            MovieAsyncListener.onTaskCompleted(data);

        }

    }
}
