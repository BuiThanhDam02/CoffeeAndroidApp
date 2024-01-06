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
    TextView txtTotalPrice, txtAddress;
    Button btnPay;
    List<CartGroupItem> cartGroupItemList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = findViewById(R.id.total);
        txtAddress = findViewById(R.id.address);
        btnPay = findViewById(R.id.btnPayment);

        cartGroupItemList = new Database(this).getCart();
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

            order.setAddress(txtAddress.getText().toString());

            System.out.println("Order này: " + order);
            Call<Order> call = Network.getInstance().create(ApiService.class).checkout(order);
            call.enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(Cart.this, "Đơn hàng đã được xác nhận!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Cart.this, "Xảy ra lỗi khi đặt hàng!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    Toast.makeText(Cart.this, "Network Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        new Database(getBaseContext()).cleanCart();
//        Toast.makeText(Cart.this, "Đơn hàng đã được xác nhận", Toast.LENGTH_SHORT).show();
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
        new Database(this).deleteItem(0);
        cartGroupItemList.remove(position);
        loadCart();
    }

    @Override
    public void onChangeQuantity(int position, int newQuantity) {
       Database db = new Database(this);
//       db.changeQuantity(cartGroupItemList.get(0).getCartItemList().get(position),
//               cartGroupItemList.get(0).getSupplierID(),
//               newQuantity);
        db.changeQuantity(cartGroupItemList.get(0).getCartItemList().get(position), newQuantity);
        cartGroupItemList = db.getCart();
        loadCart();
    }

    @Override
    public Context getContext() {
        return this;
    }
}