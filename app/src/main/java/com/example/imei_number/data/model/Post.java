package com.example.imei_number.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("IMEI")
    @Expose
    private String IMEI;

    public String getTitle() {
        return phoneNumber;
    }

    public void setTitle(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBody() {
        return IMEI;
    }

    public void setBody(String IMEI) {
        this.IMEI = IMEI;
    }


}