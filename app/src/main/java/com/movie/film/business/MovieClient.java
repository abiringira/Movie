package com.movie.film.business;

public interface MovieClient {
    //https://api.themoviedb.org/3/genre/movie/list?language=en-US&api_key=45d139dbb35cd83f615ff5c35a7c65b7
    //https://api.themoviedb.org/3/movie/now_playing?api_key=45d139dbb35cd83f615ff5c35a7c65b7&language=en-US&page=1
    public final String URL = "";
    public final String SIGNUP_RELATIVE_PATH = "";
    public final String LOGIN_RELATIVE_PATH = "";
    public final String EXPENSE_CLAIM_PATH = "";
    public final String PING_SERVER_RELATIVE_PATH = "https://api.themoviedb.org/3/genre/movie";
    public final String USER_ACTIVITIES_PATH = "https://api.themoviedb.org/3/movie/now_playing?api_key=45d139dbb35cd83f615ff5c35a7c65b7&language=en-US&page=1";
    public final String RESET_PASSWORD_PATH = "";

    /*
     * Extras for intents:
     */
    public final String EXTRA_SERVICE_TYPE = "SERVICE_TYPE";
    public final String EXTRA_SERVICE_DATA = "SERVICE_DATA";
    public final String EXTRA_ACTION_TYPE = "ACTION_TYPE";
    public final String EXTRA_USER_DATA = "USER_DATA";
    public final String EXTRA_USER_DATA_BUNDLE = "USER_DATA_BUNDLE";
    public final String ACTIVITY_ITEM_CLICK = "Transaction Details";

    public enum MovieServiceType {
        PING,
        SIGN_UP,
        LOGIN,
        LOGOUT,
        PASSWORD_RESET,
        EXPENSE_CLAIM,
        USER_ACTIVITIES
    }

    boolean isDeviceConnected();
}
