package com.movie.film.business.service;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.film.activities.MovieAsyncListenerManyItem;
import com.movie.film.business.MovieClient;
import com.movie.film.model.MovieResult;
import com.movie.film.model.MovieActivityRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MovieUserActivitiesController extends MovieBaseControllerManyItem {
    private final RestTemplate restTemplate = new RestTemplate();


    public MovieUserActivitiesController(MovieAsyncListenerManyItem asyncListener) {
        super(asyncListener);
    }


    @Override
    protected MovieResult workWithService() {
        MovieResult dataResponse = null;
        ResponseEntity<MovieResult> response = null;
        if (this.request != null) {
            try {
                List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
                messageConverters.add(new MappingJackson2HttpMessageConverter());
                restTemplate.setMessageConverters(messageConverters);
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                switch (super.serviceType) {
                    case USER_ACTIVITIES:
                        final HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

                        //ObjectMapper mapper = new ObjectMapper();
                       // mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        MovieActivityRequest movieRequestData = (MovieActivityRequest) request;
                        HttpEntity<MovieActivityRequest> entity = new HttpEntity<>(movieRequestData, headers);
                        Log.e("chek:","Catching the Json");

                        response = restTemplate.postForEntity(MovieClient.USER_ACTIVITIES_PATH,entity, MovieResult.class);
                        Log.e("chek:","After the Json");
                        break;
                        default:
                        break;
                }
                HttpStatus status = (response != null) ? response.getStatusCode() : HttpStatus.FAILED_DEPENDENCY;
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
                if (dataElement instanceof MovieActivityRequest) {
                    Log.e("MAPPING", "FINDING BUG");
                    expenseData =  this.workWithService();
                }
            }
        } catch (Throwable exc) {
            Log.e("Do In", exc.getMessage());
        }

        return expenseData;
    }


    @Override
    protected void onPreExecute() {
        asyncListener.onTaskStarted();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostExecute(MovieResult response) {
        if (response != null) {


                Log.e("Contro", "Processing on post execute." + response.getResults().toString());
            //String jsonStr = response.getMovie().toString().replace(":","");
            //String jsonFinal = jsonStr.replace("=",":");
            //String jsonFinal2 = jsonFinal.replace("%", "n");
            //String jsonFinal3 = jsonFinal2.replace("-","");
           // String jsonFinal4 = jsonFinal3.toLowerCase();


            //Log.e("Contro", "Processing on post test execute." + jsonFinal4);
           // ObjectMapper mapper = new ObjectMapper();
            //try {
               // String jsonFinal5 = URLEncoder.encode(jsonFinal4, StandardCharsets.UTF_8.toString());
                //byte ptext[] = jsonFinal5.getBytes("UTF8");
                //String value = new String(ptext, "UTF8");
                //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
               // mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
               // String str = value.trim();
               // String str2 = str.replace("%", "");
               // String str3 = str2.replace("B"," ");
                //jsonFinal5.setRequ("Accept", "image/png");

               // MovieResult jsonObj = mapper.readValue(str3, MovieResult.class);
                //mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                //Collection<Movie> readValues = mapper.readValue(
                       // response.getMovie().toString(), new TypeReference<Movie>() { }
                //);

                //for (Movie itr : jsonObj) {
                    asyncListener.onTaskCompleted(response.getResults());
                    Log.e("RESPONSE",response.getResults().toString());

               // }

           // } catch (IOException e) {
             //   e.printStackTrace();
           // }








        } else {
            Log.e("cONT", "Missing response data for user controller.");
            Queue<? extends Object> missingUser = new LinkedBlockingQueue<>();
            //missingUser.setErrorMessage("Unable to process request.");
            asyncListener.onTaskCompleted(missingUser);
        }

    }

    /* protected void onPostExecute(Queue<? extends Object> response) {
        if (response != null) {
            if (response instanceof Queue) {
                Log.d("Contro", "Processing on post execute." + response.size());
                asyncListener.onTaskCompleted((Queue<? extends Object>) response);
            }
        } else {
            Log.e("cONT", "Missing response data for user controller.");
            Queue<? extends Object> missingUser = new LinkedBlockingQueue<>();
            //missingUser.setErrorMessage("Unable to process request.");
            asyncListener.onTaskCompleted(missingUser);
        }
    }*/

    public class MyParameterizedTypeImpl implements ParameterizedType {
        private ParameterizedType delegate;
        private Type[] actualTypeArguments;

        MyParameterizedTypeImpl(ParameterizedType delegate, Type[] actualTypeArguments) {
            this.delegate = delegate;
            this.actualTypeArguments = actualTypeArguments;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return actualTypeArguments;
        }

        @Override
        public Type getRawType() {
            return delegate.getRawType();
        }

        @Override
        public Type getOwnerType() {
            return delegate.getOwnerType();
        }

    }




}
