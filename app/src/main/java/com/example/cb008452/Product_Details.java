package com.example.cb008452;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Product_Details extends Fragment {
TextView title,price;
private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product__details, container, false);
        title = view.findViewById(R.id.featured_title);
        price = view.findViewById(R.id.featured_price);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            Product_DetailsArgs args = Product_DetailsArgs.fromBundle(getArguments());
    String Title = args.getTitle();
    title.setText( args.getTitle());
    price.setText(args.getPrice());
        }
   }
}