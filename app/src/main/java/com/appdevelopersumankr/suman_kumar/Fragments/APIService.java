package com.appdevelopersumankr.suman_kumar.Fragments;

import com.appdevelopersumankr.suman_kumar.Notifications.MyResponse;
import com.appdevelopersumankr.suman_kumar.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    //"Authorization:key=AIzaSyBZ8Oy2StEpFMJtK-x2TY1KU0rnZvV5ONg"
                    "AIzaSyBZ8Oy2StEpFMJtK-x2TY1KU0rnZvV5ONg"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
