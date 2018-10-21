package com.task.soleeklab.HomeActivity;

import com.task.soleeklab.Data.Country;

import java.util.ArrayList;

public interface HomeContract {
    interface HomeView{
        void logout();
        void showCountryList(ArrayList<Country> countries);
        boolean isOnline();
        void showConnectionErorrMessage();
    }
    interface Presenter{
        void getCountryList();
    }
    interface getCountriesIntractor {
        interface OnFinishedListener {
            void onFinished(ArrayList<Country> CountryArrayList);
            void onFailure(Throwable t);
        }

        void getCountryArrayList(OnFinishedListener onFinishedListener);
    }

}

