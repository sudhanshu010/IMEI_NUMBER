package com.example.imei_number.data.model.remote;

import com.example.imei_number.data.model.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("/test/")
    @FormUrlEncoded
    Call<Post> saveData(@Field("phoneNumber") String phoneNumber, @Field("IMEI") String IMEI);
}
