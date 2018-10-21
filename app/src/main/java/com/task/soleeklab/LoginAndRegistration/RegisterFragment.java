package com.task.soleeklab.LoginAndRegistration;

import android.content.Context;
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
import com.task.soleeklab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends Fragment {
    public RegisterFragment(){}

    public interface OnLoginClickListener {
        void onLoginClick();
    }

    OnLoginClickListener mCallBack;
    public static String TAG ="AuthenticationSate";
    private FirebaseAuth mAuth;
    @BindView(R.id.email_et)EditText emailEditText;
    @BindView(R.id.password_et)EditText passwordEditText;
    @BindView(R.id.password_confirmation_et)EditText passwordConfirmEditText;
    @BindView(R.id.login_bt)TextView logInButton;
    @BindView(R.id.register_bt)Button registerButton;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack=(OnLoginClickListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"Must implement OnLoginClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.register_fragment,container,false);
        ButterKnife.bind(this, rootView);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.register);
        mAuth = FirebaseAuth.getInstance();
        registerButton.setEnabled(false);
        checkEditTextsNotEmpty();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                String passwordConfirm=passwordConfirmEditText.getText().toString();
                if(isEmailValid(email)&&password.length()>5&&passwordConfirm.length()>5&&password.matches(passwordConfirm)){
                    register(email,password);
                }else {
                    if(!isEmailValid(email)){
                        emailEditText.setError("A valid email is required.");
                    }
                    if(password.length()<6){
                        passwordEditText.setError("Password must be at least 6 characters.");
                    }
                    if(passwordConfirm.length()<6){
                        passwordConfirmEditText.setError("Password must be at least 6 characters.");
                    }
                    if(!password.matches(passwordConfirm)){
                        passwordConfirmEditText.setError("Not matches the password");
                    }
                }
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onLoginClick();
            }
        });
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(getActivity(),"logged",Toast.LENGTH_SHORT).show();
        }

    }
    private void register(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getActivity(),"logged",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(),"notlogged",Toast.LENGTH_SHORT).show();
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
                if (s.toString().length() > 0&&passwordEditText.getText().toString().length()>0&&passwordConfirmEditText.getText().toString().length()>0) {
                    registerButton.setEnabled(true);
                }else {
                    registerButton.setEnabled(false);
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
                if (s.toString().length() > 0&&passwordEditText.getText().toString().length()>0&&passwordConfirmEditText.getText().toString().length()>0) {
                    registerButton.setEnabled(true);
                }else {
                    registerButton.setEnabled(false);
                }


            }

        });
        passwordConfirmEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0&&passwordEditText.getText().toString().length()>0&&passwordConfirmEditText.getText().toString().length()>0) {
                    registerButton.setEnabled(true);
                }else {
                    registerButton.setEnabled(false);
                }


            }

        });
    }

}
