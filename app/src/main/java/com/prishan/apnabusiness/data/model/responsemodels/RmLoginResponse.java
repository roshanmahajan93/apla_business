package com.prishan.apnabusiness.data.model.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prishan.apnabusiness.data.model.User;

public class RmLoginResponse extends RmResultResponse {

    @SerializedName("response")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
