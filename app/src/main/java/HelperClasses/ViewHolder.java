package HelperClasses;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cb008452.R;
import com.squareup.picasso.Picasso;

import models.Product;

public class ViewHolder extends RecyclerView.ViewHolder {

   View view;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    view = itemView;


    }

    public void getDetails(Context context, Product product) {
        ImageView image;
        TextView Title,price;
        image = itemView.findViewById(R.id.featured_image);
        Title = itemView.findViewById(R.id.featured_title);
        price = itemView.findViewById(R.id.featured_price);

        Title.setText(product.getName());
        price.setText(product.getPrice());
        Picasso.get().load(product.getImageString()).into(image);

    }
}
