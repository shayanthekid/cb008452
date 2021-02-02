package com.example.cb008452;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import HelperClasses.FeaturedAdapter;
import HelperClasses.FeaturedCallBack;
import HelperClasses.featuredHelperClass;


public class Dashboard extends Fragment implements FeaturedCallBack {

private View view;
private RecyclerView featuredRecycler;
RecyclerView.Adapter adapter;
Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view = inflater.inflate(R.layout.fragment_dashboard, container, false);
         featuredRecycler = view.findViewById(R.id.featured_recycler);

         featuredRecycler();

         return view;
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<featuredHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new featuredHelperClass(R.drawable.tshirtslimfit, "Tshirt SlimFit","2500 Rps"));
        featuredLocations.add(new featuredHelperClass(R.drawable.jacket, "Lined shirt jacket: Checkered","7000 Rps"));
        featuredLocations.add(new featuredHelperClass(R.drawable.lined, "Lined shirt jacket: Dark blue","7000 Rps"));

        adapter = new FeaturedAdapter(featuredLocations,this);
        featuredRecycler.setAdapter(adapter);


    }

    @Override
    public void onProductItemClick(int pos, ImageView img, TextView title, TextView price) {
        DashboardDirections.NavigateFromDashToProDet actions = DashboardDirections.NavigateFromDashToProDet();
        actions.setTitle((String) title.getText());
        actions.setPrice((String) price.getText());
        Navigation.findNavController(view).navigate(actions);
    //    Navigation.findNavController(view).navigate(R.id.NavigateFromDashToProDet);
//
//        Toast.makeText(getActivity().getBaseContext(), "something" + title.getText(), Toast.LENGTH_SHORT).show();
    }
    }
