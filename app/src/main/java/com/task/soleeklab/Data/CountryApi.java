package com.task.soleeklab.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApi {

    @GET("data_json.json")
    Call<ArrayList<Country>>getCountries();
}


