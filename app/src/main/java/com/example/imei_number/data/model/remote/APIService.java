package com.example.imei_number.data.model.remote;

import com.example.imei_number.data.model.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("/Project/androidcapture/")
    @FormUrlEncoded
    Call<Post> saveData(@Field("phone") String phone, @Field("IMEI") String IMEI,@Field("name") String name);
}
