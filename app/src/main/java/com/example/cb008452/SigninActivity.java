package com.example.cb008452;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import viewmodels.UserViewmodel;

public class SigninActivity extends AppCompatActivity {

    private EditText txtEmail,txtPassword;
    private Button btnRegister,btnLogin;
    private UserViewmodel userViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        userViewmodel = ViewModelProviders.of(this).get(UserViewmodel.class);

        userViewmodel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    Toast.makeText(SigninActivity.this, "User has been created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SigninActivity.this, Shop.class);
                    startActivity(intent);
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

               if(email.length() >0 && password.length() > 0){
                   userViewmodel.Register(email,password);
               }



            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();


                if(email.length() >0 && password.length() > 0){
                    userViewmodel.Login(email,password);
                }

            }
        });



    }
}