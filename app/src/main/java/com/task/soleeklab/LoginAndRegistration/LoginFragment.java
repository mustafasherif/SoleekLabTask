package com.task.soleeklab.LoginAndRegistration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.task.soleeklab.HomeActivity.HomeActivity;
import com.task.soleeklab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment {

    public LoginFragment(){}

    public interface OnRegisterClickListener {
        void onRegisterClick();
    }

    OnRegisterClickListener mCallBack;
    public static String TAG ="AuthenticationSate";
    private FirebaseAuth mAuth;
    @BindView(R.id.email_et)EditText emailEditText;
    @BindView(R.id.password_et)EditText passwordEditText;
    @BindView(R.id.login_bt)Button logInButton;
    @BindView(R.id.register_bt)TextView registerButton;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack=(OnRegisterClickListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"Must implement OnRegisterClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.login_fragment,container,false);
        ButterKnife.bind(this, rootView);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.login);
        mAuth = FirebaseAuth.getInstance();
        logInButton.setEnabled(false);
        checkEditTextsNotEmpty();
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(isEmailValid(email)&&password.length()>5){
                    signIn(email,password);
                }else {
                    if(!isEmailValid(email)){
                        emailEditText.setError("A valid email is required.");
                    }
                    if(password.length()<6){
                        passwordEditText.setError("Password must be at least 6 characters.");
                    }
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onRegisterClick();
            }
        });
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            openHome();
        }

    }
    private void signIn(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            openHome();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    void checkEditTextsNotEmpty(){
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0&&passwordEditText.getText().toString().length()>0) {
                    logInButton.setEnabled(true);
                }else {
                    logInButton.setEnabled(false);
                }

            }

        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0&&emailEditText.getText().toString().length()>0) {
                    logInButton.setEnabled(true);
                }else {
                    logInButton.setEnabled(false);
                }
            }

        });
    }
    private void openHome(){
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
