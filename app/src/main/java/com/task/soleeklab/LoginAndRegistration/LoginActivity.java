package com.task.soleeklab.LoginAndRegistration;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.task.soleeklab.R;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnRegisterClickListener,RegisterFragment.OnLoginClickListener{
    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginFragment=new LoginFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment,loginFragment).commit();
    }
    @Override
    public void onRegisterClick() {
        registerFragment=new RegisterFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment,registerFragment).addToBackStack(null).commit();
    }

    @Override
    public void onLoginClick() {
        fragmentManager.beginTransaction().replace(R.id.fragment,loginFragment).commit();
    }

}
