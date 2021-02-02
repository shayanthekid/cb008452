package com.example.cb008452;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import models.User;
import viewmodels.LoggedInViewModel;
import viewmodels.LoginViewModel;
import viewmodels.UserViewmodel;

public class LoginActivity extends AppCompatActivity {
    private ImageView LoginBtnBackButton;
    private EditText txtEmail,txtPassword;
    private FirebaseAuth mAuth;
    private Button btnLogin;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        mAuth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.btnLogin);
        LoginBtnBackButton = findViewById(R.id.LoginBtnBackButton);
        getSupportActionBar().hide();

        LoginBtnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),ChoiceActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(LoginBtnBackButton,"transition_back");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent,options.toBundle());

                }
                else{
                    startActivity(intent);
                }

            }
        });




btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
         String Name = txtEmail.getText().toString();
     String Password = txtPassword.getText().toString();
        Login(Name,Password);

    }
});

    }


  public void Login(final String Email,final String Password){

      mAuth.signInWithEmailAndPassword(Email,Password)
              .addOnCompleteListener(ContextCompat.getMainExecutor(this), new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {

                      if(task.isSuccessful()){
                          Toast.makeText(LoginActivity.this, "Login Success",Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
                          startActivity(intent);



                      }
                      else{
                          Toast.makeText(LoginActivity.this, "Login failed",Toast.LENGTH_SHORT).show();


                      }

                  }
              });


  }
/*
*
* OLD LOGIN CODE
* */

//
//      Query query=  mDatabase.child("userAuth");
//      query.addValueEventListener(new ValueEventListener() {
//          @Override
//          public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
//           try {
//
//
//              String name = Name;
//              String ID = dataSnapshot.child(name).getValue().toString();
//              Query query = mDatabase.child("users").child(ID);
//              query.addValueEventListener(new ValueEventListener() {
//                  @Override
//                  public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
//                      User user = dataSnapshot2.getValue(User.class);
//
//                      try {
//                          if(Password.equals( user.getPassword())){
//                              Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                          }
//                          else {
//                              Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
//                              System.out.println(user.getPassword());
//                          }
//                      }catch (NullPointerException e){
//                          Toast.makeText(LoginActivity.this, "Please enter details", Toast.LENGTH_SHORT).show();
//                      }
//
//                  }
//
//                  @Override
//                  public void onCancelled(@NonNull DatabaseError databaseError2) {
//                      Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
//                  }
//              });
//           }catch (NullPointerException e){
//               Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
//           }
//
//          }
//
//          @Override
//          public void onCancelled(@NonNull DatabaseError databaseError) {
//
//          }
//      });









}