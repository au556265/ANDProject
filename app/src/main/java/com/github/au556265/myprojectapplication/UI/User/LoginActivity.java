package com.github.au556265.myprojectapplication.UI.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.au556265.myprojectapplication.R;
import com.github.au556265.myprojectapplication.UI.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private FirebaseAuth mAuth;
    private EditText mEmail;
    private EditText mPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        setContentView(R.layout.fragment_login);
        Button signUp = findViewById(R.id.registerButton);
        Button login = findViewById(R.id.loginButton);

        mEmail = findViewById(R.id.loginEmail);
        mPassword = findViewById(R.id.loginPassword);

        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickLogin();
            }
        });


        signUp.setOnClickListener(v -> {
            Log.i("Signup", "register");
            Intent intent = new Intent(this, RegisterUser.class);
            //Send some data between activities use putExtra
            startActivity(intent);
        });
    }

    private void OnClickLogin() {
        String email =mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            checkIfSignedIn();
                            Toast.makeText(LoginActivity.this,"Login Succeded",Toast.LENGTH_LONG).show();
                            // Sign in success, update UI with the signed-in user's information
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


   private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null)
            goToNewView();
        });
    }

    private void goToNewView(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }



    }