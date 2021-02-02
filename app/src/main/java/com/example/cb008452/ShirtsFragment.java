package com.example.cb008452;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import HelperClasses.ViewHolder;
import models.Product;

public class ShirtsFragment extends Fragment {
    Context context;
    private View view;
    private StorageReference mStorageRef;
    private FirebaseRecyclerAdapter adapter;
    private DatabaseReference mDatabase;
    private RecyclerView shirtRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_shirts, container, false);

        shirtRecyclerView = view.findViewById(R.id.shirtRecyclerView);


        mDatabase = FirebaseDatabase.getInstance().getReference("products");
        mStorageRef = FirebaseStorage.getInstance().getReference("products");
        Query query =  FirebaseDatabase.getInstance().getReference("products");

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(query,Product.class)
                .build();

         adapter = new FirebaseRecyclerAdapter<Product, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull Product model) {

holder.Title.setText(model.getName());
holder.price.setText(Integer.toString(model.getPrice()) +" Rps");
Glide.with(holder.image.getContext()).load(model.getImageString()).into(holder.image);
//                Picasso.get().load(model.getImageString()).into(holder.image);
//Picasso.get().load(model.getImageString()).error(R.drawable.common_google_signin_btn_icon_dark).into(holder.image);

//Glide.with(holder.image.getContext()).load(model.getImageString()).into(holder.image);
            }
        };

        shirtRecyclerView.setHasFixedSize(true);
        shirtRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        shirtRecyclerView.setAdapter(adapter);
        return view;
    }


    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView Title,price;



        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.featured_image);
            Title = itemView.findViewById(R.id.featured_title);
            price = itemView.findViewById(R.id.featured_price);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}



