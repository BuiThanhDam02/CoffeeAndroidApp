package com.example.smartcoffeecourt.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.CoffeeAdapter;
import com.example.smartcoffeecourt.Adapter.GreatFoodAdapter;
import com.example.smartcoffeecourt.Adapter.StallAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.CoffeeDetail.CoffeeDetailPage;
import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.GreatCoffeeViewHolder;
import com.example.smartcoffeecourt.ViewHolder.StallViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    RecyclerView greatFoodRecycler, stallRecycler;
    FirebaseDatabase database;
    DatabaseReference foodList;

    List<Stall> stallList;
    List<Coffee> coffeeList;
    FirebaseRecyclerAdapter<Coffee, GreatCoffeeViewHolder> adapterGreatFood;
    StallAdapter adapterStall ;


    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food/List");
        stallList = new ArrayList<>();
        greatFoodRecycler = root.findViewById(R.id.great_food_recycler);
        stallRecycler = root.findViewById(R.id.stall_recycler);
        adapterStall = new StallAdapter(getActivity(),stallList,getParentFragmentManager());
        stallRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        stallRecycler.setAdapter(adapterStall);

        greatFoodRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        loadGreatFoodList();
        loadStallList();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        //adapterGreatFood.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        //adapterGreatFood.stopListening();
    }

    private void loadStallList()  {

        Call<List<Stall>> call = Network.getInstance().create(ApiService.class).getAllStallDung();
        call.enqueue(new Callback<List<Stall>>() {
            @Override
            public void onResponse(Call<List<Stall>> call, Response<List<Stall>> response) {
                if (response.isSuccessful()) {
                   stallList.addAll(response.body());
                   adapterStall.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Stall>> call, Throwable t) {
                System.out.println("wrong network");           }
        });
    }

    private void loadGreatFoodList() {
        Call<List<Coffee>> call  = Network.getInstance().create(ApiService.class).getGreatCoffee();
        call.enqueue(new Callback<List<Coffee>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                if(response.body() != null) {
                    coffeeList = response.body();
                    System.out.println(coffeeList);
                    GreatFoodAdapter coffeeAdapter1 = new GreatFoodAdapter(coffeeList, getContext());
                    coffeeAdapter1.notifyDataSetChanged();
                    greatFoodRecycler.setAdapter(coffeeAdapter1);
                }
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
                Toast.makeText(getContext(), "Call Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}