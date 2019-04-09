package com.debbysa.tugasretrofitapi.services;

import com.debbysa.tugasretrofitapi.model.Fox;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoxService {
    @GET("/floof/")
    Call<Fox> getRandomNasa();
}
