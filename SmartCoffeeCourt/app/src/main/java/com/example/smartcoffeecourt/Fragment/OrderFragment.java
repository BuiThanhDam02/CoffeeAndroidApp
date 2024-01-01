package com.example.smartcoffeecourt.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.OrderAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OrderFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Order> orderList;
    OrderAdapter adapterOrder;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        orderList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadOrder(Integer.valueOf(Common.userId));
        return root;
    }

    private void loadOrder(Integer userId){
        Call<List<Order>> call = Network.getInstance().create(ApiService.class).getAllOrderByUserId(userId);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    orderList = response.body();
                    
                    System.out.println(orderList);
                    adapterOrder = new OrderAdapter(getContext(),orderList);
                    recyclerView.setAdapter(adapterOrder);
                    adapterOrder.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                System.out.println("wrong network");           }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}