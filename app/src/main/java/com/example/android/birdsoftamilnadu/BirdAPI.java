package com.example.android.birdsoftamilnadu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface BirdAPI {

    String BASE_URL = "https://api.myjson.com/bins/";

    @GET("151rtx")
    Call<List<BirdModel>> getBirds();

}
