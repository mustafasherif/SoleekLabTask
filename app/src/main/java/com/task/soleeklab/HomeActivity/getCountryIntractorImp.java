package com.task.soleeklab.HomeActivity;


import com.task.soleeklab.Data.Country;
import com.task.soleeklab.Data.CountryApi;
import com.task.soleeklab.Data.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getCountryIntractorImp implements HomeContract.getCountriesIntractor {
    @Override
    public void getCountryArrayList(final OnFinishedListener onFinishedListener) {
        CountryApi service = RetrofitInstance.getRetrofitInstance().create(CountryApi.class);
        Call<ArrayList<Country>> call = service.getCountries();
        call.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
