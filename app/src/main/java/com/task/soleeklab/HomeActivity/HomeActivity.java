package com.task.soleeklab.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.task.soleeklab.CountryAdapter;
import com.task.soleeklab.Data.Country;
import com.task.soleeklab.LoginAndRegistration.LoginActivity;
import com.task.soleeklab.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.HomeView {

    @BindView(R.id.recycler_view)RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private HomeContract.Presenter presenter;
    public static CountryAdapter countryAdapter;
    private ArrayList<Country> countryArrayList;
    public static AppCompatActivity hh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle("Home");
        countryArrayList=new ArrayList();
        countryAdapter=new CountryAdapter(countryArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(countryAdapter);
        presenter=new HomePresenter(this,new getCountryIntractorImp());
        presenter.getCountryList();
        mAuth=FirebaseAuth.getInstance();
        hh=this;

        }

    public void Logout(View view) {
    logout();
    }

    @Override
    public void logout() {
        if(mAuth.getCurrentUser()!=null)
            mAuth.signOut();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showCountryList(ArrayList<Country>countries) {
        countryAdapter.changeSorting(countries);
        Toast.makeText(HomeActivity.hh,"kkk"+countries.get(2).getName(),Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void showConnectionErorrMessage() {
        Toast.makeText(this,"Check you connection",Toast.LENGTH_SHORT).show();
    }
}
