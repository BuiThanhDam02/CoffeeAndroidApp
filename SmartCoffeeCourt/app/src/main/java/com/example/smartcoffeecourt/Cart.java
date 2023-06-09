package com.example.smartcoffeecourt;

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
import com.example.smartcoffeecourt.Database.Database;
import com.example.smartcoffeecourt.Model.CartGroupItem;
import com.example.smartcoffeecourt.Model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Cart extends AppCompatActivity implements CartAdapter.CartGroupItemListener{

    RecyclerView recyclerView;
    DatabaseReference requestReference;
    TextView txtTotalPrice;
    Button btnPay;
    List<CartGroupItem> cartGroupItemList;

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
            Order order =  new Order(Common.user.getPhone(), t);
            requestReference.push().setValue(order);
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
    public void onTypeChangeClick(int position, String newType) {
        cartGroupItemList.get(position).setType(newType);
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