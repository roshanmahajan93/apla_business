package com.prishan.apnabusiness.data.model.requestmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RmLoginRequest {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    public RmLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
