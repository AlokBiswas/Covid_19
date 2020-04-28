package com.alokbiswas.covid_19.api;

import com.alokbiswas.covid_19.model.Mundial;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("all")
    Call<Mundial> getInfoMundial();
}
