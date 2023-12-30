package com.example.smartcoffeecourt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.CoffeeAdapter;
import com.example.smartcoffeecourt.Adapter.CoffeeOrderDetailAdapter;
import com.example.smartcoffeecourt.Adapter.OrderAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.CoffeeOrderDetail;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Model.OrderDetailModel;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.Network.Network;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderDetail extends AppCompatActivity {

    RecyclerView recyclerCoffee;
    CoffeeOrderDetailAdapter coffeeAdapter;
    List<CoffeeOrderDetail> coffeeOrderDetails;
    OrderDetailModel orderDetail;

    TextView orderStatus,orderAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_detail);

        orderStatus = findViewById(R.id.order_status);
        orderAddress = findViewById(R.id.order_address);

        recyclerCoffee = findViewById(R.id.recycler_coffee);
        recyclerCoffee.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        orderDetail = new OrderDetailModel();
        coffeeOrderDetails = new ArrayList<>();
        //coffeeAdapter = new CoffeeOrderDetailAdapter(coffeeOrderDetails,getApplicationContext());
        //recyclerCoffee.setAdapter(coffeeAdapter);

        Intent intent = getIntent();
        Long id = Long.valueOf(intent.getStringExtra("id"));
        loadData(id);
    }

    private void loadData(Long orderId) {

        Call<OrderDetailModel> call = Network.getInstance().create(ApiService.class).getDetailOrder(orderId);
        call.enqueue(new Callback<OrderDetailModel>() {
            @Override
            public void onResponse(Call<OrderDetailModel> call, Response<OrderDetailModel> response) {
                if (response.isSuccessful()) {
                    orderDetail = response.body();

                    System.out.println(orderDetail);
                    orderAddress.setText("Địa chỉ \n" + orderDetail.getOrder().getAddress());
                    //coffeeOrderDetails.addAll(orderDetail.getCoffees());
                    //coffeeOrderDetails = orderDetail.getCoffees();
                    coffeeAdapter = new CoffeeOrderDetailAdapter(orderDetail.getCoffees(),getApplication());

                    coffeeAdapter.notifyDataSetChanged();
                    recyclerCoffee.setAdapter(coffeeAdapter);
                }
            }
            @Override
            public void onFailure(Call<OrderDetailModel> call, Throwable t) {
                System.out.println("wrong network");           }
        });
    }

}