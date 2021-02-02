package com.example.cb008452;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class ShopActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
LinearLayout contentView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop2);
        getSupportActionBar().hide();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);

        //Navigation Drawer
       navigationDrawer();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
      drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
          @Override
          public void onDrawerSlide(View drawerView, float slideOffset) {
              super.onDrawerSlide(drawerView, slideOffset);
              final float diffScaledOffset = slideOffset * (1 - END_SCALE);
              final float offsetScale = 1 - diffScaledOffset;
              contentView.setScaleX(offsetScale);
              contentView.setScaleY(offsetScale);

              // Translate the View, accounting for the scaled width
              final float xOffset = drawerView.getWidth() * slideOffset;
              final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
              final float xTranslation = xOffset - xOffsetDiff;
              contentView.setTranslationX(xTranslation);
          }
      });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       switch (menuItem.getItemId()){
           case R.id.nav_home:
               FragmentManager fm0 = getSupportFragmentManager();
               Dashboard fragment0 = new Dashboard();
               fm0.beginTransaction().replace(R.id.fragment,fragment0).commit();
               break;
           case R.id.nav_shirts:
               FragmentManager fm = getSupportFragmentManager();
            ShirtsFragment fragment = new ShirtsFragment();
            fm.beginTransaction().replace(R.id.fragment,fragment).commit();
            break;

           case R.id.nav_pants:
               FragmentManager fm2 = getSupportFragmentManager();
               Categories fragment2 = new Categories();
               fm2.beginTransaction().replace(R.id.fragment,fragment2).commit();
               break;

       }

        return true;
    }
}