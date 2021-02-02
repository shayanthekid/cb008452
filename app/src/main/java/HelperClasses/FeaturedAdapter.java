package HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cb008452.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<featuredHelperClass> featuredLocations;
    private FeaturedCallBack featuredCallBack;

    public FeaturedAdapter(ArrayList<featuredHelperClass> featuredLocations, FeaturedCallBack featuredCallBack) {
        this.featuredLocations = featuredLocations;
        this.featuredCallBack = featuredCallBack;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        featuredHelperClass featuredHelperClass = featuredLocations.get(position);
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.Title.setText(featuredHelperClass.getTitle());
        holder.price.setText(featuredHelperClass.getPrice());


    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public class FeaturedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView Title,price;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.featured_image);
            Title = itemView.findViewById(R.id.featured_title);
            price = itemView.findViewById(R.id.featured_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ImageView Image = image;
            TextView title = Title;
            TextView Price = price;
            featuredCallBack.onProductItemClick(position,Image,title,Price);
        }
    }

}