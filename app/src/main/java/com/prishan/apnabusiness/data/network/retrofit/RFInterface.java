package com.prishan.apnabusiness.data.network.retrofit;


import com.prishan.apnabusiness.data.model.requestmodels.RmLoginRequest;
import com.prishan.apnabusiness.data.model.responsemodels.RmLoginResponse;
import com.prishan.apnabusiness.util.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RFInterface {

    /*@POST("auth/Request")
    Call<RmGetSessionResponse> requestSession(@Header(Constant.Header.AUTH_ID) String authID, @Body RmGetSessionRequest sessionRequest);

    @GET("auth/Validate")
    Call<RmValidateSessionResponse> validateSession(@Header(Constant.Header.AUTH_ID) String authID);*/

    @POST("login/Authenticate")
    Call<RmLoginResponse> checkLogin(@Body RmLoginRequest loginRequest);

    /*@POST("rom/ROAll")
    Call<RmAllRoResponse> getROData(@Header(Constant.Header.AUTH_ID) String authID, @Header(Constant.Header.SESSION_ID) String sessionID, @Body RmAllRoRequest approvedRoRequest);

    @GET("rom/Movies")
    Call<RmAllMoviesResponse> getAllMovies(@Header(Constant.Header.AUTH_ID) String authID, @Header(Constant.Header.SESSION_ID) String sessionID);*/


}
