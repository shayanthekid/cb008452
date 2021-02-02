package com.example.cb008452;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        txt.startAnimation(myanim);

    getSupportActionBar().hide();

    /*To see if the user is logged in or not, must implement shared preference for onboardscreen on install*/
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            if(currentUser !=null){
                System.out.println(currentUser);
            }

//            if(currentUser !=null)
//            {
//                Intent intent = new Intent(MainActivity.this, Shop.class);
//                startActivity(intent);
//            } else {
//                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
//                startActivity(intent);
//            }
//            Intent intent = new Intent(MainActivity.this, welcome.class);
            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
//            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            startActivity(intent);
            finish();
        }
    },2500);
    }
}