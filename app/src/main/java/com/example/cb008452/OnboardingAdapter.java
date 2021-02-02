package com.example.cb008452;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter  extends  RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>{

    private List<OnboardingItem> onboardingItem;

    public OnboardingAdapter(List<OnboardingItem> onboardingItem) {
        this.onboardingItem = onboardingItem;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {

        holder.setOnboardingData(onboardingItem.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItem.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle;
        private TextView txtDescription;
        private ImageView imageOnboarding;

         OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imageOnboarding = itemView.findViewById(R.id.imageOnboarding);
        }

       void setOnboardingData(OnboardingItem onboardingItem){
    txtTitle.setText(onboardingItem.getTitle());
    txtDescription.setText(onboardingItem.getDescription());
    imageOnboarding.setImageResource(onboardingItem.getImage());

       }


    }
}
