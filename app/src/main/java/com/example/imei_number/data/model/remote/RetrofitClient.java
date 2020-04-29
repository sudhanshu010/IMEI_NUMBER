package com.example.imei_number.data.model.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Gson gson;
    public static Retrofit retrofit=null;
    public static Retrofit getClient(String baseUrl){
        if(retrofit==null){
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit= new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
