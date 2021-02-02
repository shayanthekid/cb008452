package com.example.cb008452;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chabbal.slidingdotsplash.SlidingSplashView;
import com.google.android.material.button.MaterialButton;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

import viewmodels.welcomeViewModel;

public class welcome extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton btnOnboardingAction;
    private welcomeViewModel mwelcomeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        layoutOnboardingIndicators= findViewById(R.id.LayoutOnboardingIndicators);
        btnOnboardingAction = findViewById(R.id.btnOnboardAction);
        mwelcomeViewModel = ViewModelProviders.of(this).get(welcomeViewModel.class);

//        mwelcomeViewModel.getOnboardingItem().observe(this, new Observer<List<OnboardingItem>>() {
//            @Override
//            public void onChanged(List<OnboardingItem> onboardingItems) {
//
//            }
//        });

        mwelcomeViewModel.init();

        setupOnboardingItems();
        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);
        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);

            }
        });

        btnOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }else{
                startActivity(new Intent(getApplicationContext(),ChoiceActivity.class));
               finish();
                }
            }
        });

    }

    private void setupOnboardingItems() {
//        List<OnboardingItem> onboardingItems = new ArrayList<>();
//
//        OnboardingItem ladyCart = new OnboardingItem();
//        ladyCart.setTitle("Welcome to LookGOOD!");
//        ladyCart.setDescription("Clothes shopping has never been easier! Hassle free buying for all your needs!");
//        ladyCart.setImage(R.drawable.ladywithcart);
//
//        OnboardingItem ladyBags = new OnboardingItem();
//        ladyBags.setTitle("LookGOOD to feel good");
//        ladyBags.setDescription("Shop with us to leave you feeling satisfied.Always!");
//        ladyBags.setImage(R.drawable.ladywithbag);
//
//
//        OnboardingItem ladyDress = new OnboardingItem();
//        ladyDress.setTitle("Curated Selections");
//        ladyDress.setDescription("Our vast catalogue of options will leave you wanting for more!");
//        ladyDress.setImage(R.drawable.ladywithdress);
//
//        onboardingItems.add(ladyCart);
//        onboardingItems.add(ladyBags);
//        onboardingItems.add(ladyDress);

        onboardingAdapter = new OnboardingAdapter(mwelcomeViewModel.getOnboardingItem().getValue());

    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i =0; i < indicators.length; i++){

        indicators[i] = new ImageView(getApplicationContext());
        indicators[i].setImageDrawable(ContextCompat.getDrawable(
                getApplicationContext(),
                R.drawable.onboarding_indicator_inactive
        ));
        indicators[i].setLayoutParams(layoutParams);
        layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicator(int index){
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition2);
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i = 0;i < childCount; i++){
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if(i == index){
                imageView.startAnimation(myanim);
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.startAnimation(myanim);
    imageView.setImageDrawable(
            ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
    );
            }
        }
        if(index == onboardingAdapter.getItemCount()-1){
            btnOnboardingAction.setText("Continue");
        }else{
            btnOnboardingAction.setText("Next");
        }
    }

}