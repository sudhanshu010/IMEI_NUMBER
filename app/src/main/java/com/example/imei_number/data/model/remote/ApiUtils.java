package com.example.imei_number.data.model.remote;

public class ApiUtils {
    public ApiUtils() {
    }

    public static final String BASE_URL="https://whats-app-bot-99.herokuapp.com/";
    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
