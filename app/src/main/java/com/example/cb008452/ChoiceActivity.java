package com.example.cb008452;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        getSupportActionBar().hide();



    }

    public void callLoginScreen(View view){

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.btnLogin),"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChoiceActivity.this, pairs);
      startActivity(intent,options.toBundle());

        }
        else{
            startActivity(intent);
        }


    }

    public void callSignupScreen(View view){

        Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.btnSignUp),"transition_signin");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChoiceActivity.this, pairs);
            startActivity(intent,options.toBundle());

        }
        else{
            startActivity(intent);
        }
    }


}