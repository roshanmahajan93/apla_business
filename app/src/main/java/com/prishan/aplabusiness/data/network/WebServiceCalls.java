package com.prishan.aplabusiness.data.network;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;


import com.prishan.aplabusiness.data.model.requestmodels.RmLoginRequest;
import com.prishan.aplabusiness.data.model.responsemodels.RmLoginResponse;
import com.prishan.aplabusiness.data.network.retrofit.RFInterface;
import com.prishan.aplabusiness.util.Constant;
import com.prishan.aplabusiness.util.commonui.Utility;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceCalls {

    private static final String TAG = "WebServiceCalls";

    private static RFInterface rfInterface = Utility.getRetrofitInterface(Constant.URL);

    public static class Login {
        public static void check(Context context, RmLoginRequest loginRequest,
                                 final NetworkOperations nwCall) {

            nwCall.onStart(context, "");

            rfInterface.checkLogin(loginRequest).enqueue(new Callback<RmLoginResponse>() {

                @Override
                public void onResponse(@NonNull Call<RmLoginResponse> call, @NonNull Response<RmLoginResponse> response) {
                    nwCall.onComplete();
                    if (response.body().getValid()) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.SharedPrefrence.User.USER, response.body().getUser());
                        nwCall.onSuccess(bundle);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.MESSAGE, response.body().getComment());
                        nwCall.onFailure(bundle);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RmLoginResponse> call, @NonNull Throwable t) {
                    Log.e(TAG, Objects.requireNonNull(t.getMessage()));
                    nwCall.onComplete();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.MESSAGE, t.getMessage());
                    nwCall.onFailure(bundle);
                }

            });
        }
    }

}