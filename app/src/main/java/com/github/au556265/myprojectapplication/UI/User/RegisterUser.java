package com.github.au556265.myprojectapplication.UI.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.au556265.myprojectapplication.Models.User;
import com.github.au556265.myprojectapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

    private RegisterUserViewModel viewModel;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mPhoneNumber;
    private EditText mEmail;
    private EditText mPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        viewModel = new ViewModelProvider(this).get(RegisterUserViewModel.class);
        viewModel.init();
        mFirstName = findViewById(R.id.editTextFirstName);
        mLastName = findViewById(R.id.editTextLastName);
        mPhoneNumber = findViewById(R.id.editTextViewPhone);
        mEmail = findViewById(R.id.editTextEmail);
        mPassword = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();


        Button button = findViewById(R.id.registerUserbutton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClicked();
            }
        });


    }

    private void OnClicked() {
        String email =mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String firstName =mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String phoneNumber = mPhoneNumber.getText().toString().trim();

        Boolean isValid=true;
        if(email.equals("")){
            mEmail.setError("Email is required");
            isValid=false;
        }
        if(password.equals("")){
            mPassword.setError("Password is required");
            isValid=false;
        }
        if(password.length()<6){
            mPassword.setError("Password must be at least 6 characters");
            isValid=false;
        }
        if(firstName.equals("")){
            mFirstName.setError("First name is required");
        }
        if(lastName.equals("")){
            mLastName.setError("last name is required");
        }

        if(phoneNumber.equals("")){
            mPhoneNumber.setError("Phone number is required");
        }
        if(isValid==true) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        goToNewView();
                        Toast.makeText(RegisterUser.this, "User created. ", Toast.LENGTH_LONG).show();
                        viewModel.addUser(new User(firstName, lastName, phoneNumber, email));
                    } else {
                        Toast.makeText(RegisterUser.this, "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    private void goToNewView(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}