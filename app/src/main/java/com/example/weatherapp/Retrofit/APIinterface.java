package com.example.weatherapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIinterface {

    @GET("weather?appid=a81105056ce68fa9f6c63c5a42b08ba1&units=metric")

    Call <Example> getWeatherData(@Query("q") String name);
}
