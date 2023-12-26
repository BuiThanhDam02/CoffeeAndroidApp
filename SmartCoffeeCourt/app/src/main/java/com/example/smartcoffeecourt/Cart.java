package com.example.smartcoffeecourt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.CartAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Database.Database;
import com.example.smartcoffeecourt.Model.CartGroupItem;
import com.example.smartcoffeecourt.Model.CartItem;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Network.Network;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity implements CartAdapter.CartGroupItemListener{

    RecyclerView recyclerView;
    DatabaseReference requestReference;
    TextView txtTotalPrice, txtAddress;
    Button btnPay;
    List<CartGroupItem> cartGroupItemList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        requestReference = FirebaseDatabase.getInstance().getReference("Order/CurrentOrder/List");

        recyclerView = findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = findViewById(R.id.total);
        txtAddress = findViewById(R.id.address);
        btnPay = findViewById(R.id.btnPayment);

//        cartGroupItemList = new Database(this).getCart();
        cartGroupItemList = new ArrayList<>();

        List<CartItem> cartItemList = new ArrayList<>();

        // Item 1
        CartItem item1 = new CartItem();
        item1.setCoffeeId(1l);
        item1.setName("Item 1");
        item1.setPrice("10.00");
        item1.setQuantity("2");
        item1.setDiscount("0.50");
        cartItemList.add(item1);

        // Item 2
        CartItem item2 = new CartItem();
        item2.setCoffeeId(2l);
        item2.setName("Item 2");
        item2.setPrice("15.00");
        item2.setQuantity("1");
        item2.setDiscount("1.00");
        cartItemList.add(item2);

        // Group 1
        CartGroupItem group1 = new CartGroupItem();
        group1.setSupplierID(1);
        group1.setType("1");
        group1.setCartItemList(cartItemList);
        cartGroupItemList.add(group1);



        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmOrder();
            }
        });

        loadCart();
    }
    private void confirmOrder() {
        for(CartGroupItem t: cartGroupItemList){
            Order order =  new Order(Common.user, t);
//            requestReference.push().setValue(order);
//            System.out.println("User nay: " + Common.user);
            System.out.println("Order nay: " + order);
            order.setAddress(txtAddress.getText().toString());
            System.out.println("Address nay: " + order);
            Call<Order> call = Network.getInstance().create(ApiService.class).checkout(order);
            call.enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(Cart.this, "Đơn hàng đã được xác nhận", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Cart.this, "Loi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    Toast.makeText(Cart.this, "Loi 2", Toast.LENGTH_SHORT).show();
                }
            });
        }
        new Database(getBaseContext()).cleanCart();
        Toast.makeText(Cart.this, "Đơn hàng đã được xác nhận", Toast.LENGTH_SHORT).show();
        finish();
    }
    private void loadCart() {
        CartAdapter adapter = new CartAdapter(cartGroupItemList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getBaseContext(),R.anim.layout_item_from_left));

        float total = 0;
        for (CartGroupItem cartGroupItem : cartGroupItemList) {
            total += cartGroupItem.getTotal();
        }

        txtTotalPrice.setText(Common.convertPriceToVND(total));


    }

    @Override
    public void onTypeChangeClick(int position, String newType, String newAddress) {
        cartGroupItemList.get(position).setType(newType);
        txtAddress.setText(newAddress);
        loadCart();
    }

    @Override
    public void onDeleteOrder(int position) {
        new Database(this).deleteItem(cartGroupItemList.get(position).getSupplierID());
        cartGroupItemList.remove(position);
        loadCart();
    }

    @Override
    public void onChangeQuantity(int position, int newQuantity) {
       Database db = new Database(this);
       db.changeQuantity(cartGroupItemList.get(0).getCartItemList().get(position),
               cartGroupItemList.get(0).getSupplierID(),
               newQuantity);
        cartGroupItemList = db.getCart();
        loadCart();
    }

    @Override
    public Context getContext() {
        return this;
    }
}