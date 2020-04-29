package com.example.imei_number.data.model.remote;

public class ApiUtils {
    public ApiUtils() {
    }

    public static final String BASE_URL="https://mylocationx.co.uk/";
    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
