package com.task.soleeklab.HomeActivity;

import com.task.soleeklab.Data.Country;

import java.util.ArrayList;


public class HomePresenter implements HomeContract.Presenter ,HomeContract.getCountriesIntractor.OnFinishedListener{

    private final HomeContract.HomeView mHomeView;
    private final HomeContract.getCountriesIntractor getCountriesIntractor;

    public HomePresenter(HomeContract.HomeView mHomeView,HomeContract.getCountriesIntractor getCountriesIntractor) {
        this.mHomeView =mHomeView;
        this.getCountriesIntractor = getCountriesIntractor;
    }

    @Override
    public  void getCountryList() {
        if(mHomeView.isOnline()){
            getCountriesIntractor.getCountryArrayList(this);
        }else {
            mHomeView.showConnectionErorrMessage();
        }

    }

    @Override
    public void onFinished(ArrayList<Country> CountryArrayList) {
        if(mHomeView!=null){
            mHomeView.showCountryList(CountryArrayList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (mHomeView!=null){
            mHomeView.showConnectionErorrMessage();
        }
    }
}
