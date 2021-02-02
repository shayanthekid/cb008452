package com.example.cb008452;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewAdapter extends PagerAdapter {

   private Context context;
   private LayoutInflater layoutInflater;
//   private  Integer[] images= {R.drawable}

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
