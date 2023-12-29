package com.example.smartcoffeecourt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.CoffeeDetail.CoffeeDetailPage;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.CoffeeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeViewHolder> {

    private List<Coffee> coffeeList;
    private Context context;

    public CoffeeAdapter(List<Coffee> coffeeList, Context context) {
        this.coffeeList = coffeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_item, parent, false);
        return new CoffeeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder coffeeViewHolder, int position) {
        Coffee coffee = coffeeList.get(position);
        System.out.println(coffee.toString());
        coffeeViewHolder.coffee_name.setText(coffee.getName());
        coffeeViewHolder.coffee_price.setText(Common.convertPriceToVND(Float.parseFloat(coffee.getPrice())));
        coffeeViewHolder.coffee_supplier.setText(String.format("Stall %s", coffee.getSupplier().getName()));
        String imageCoffee = coffee.getImageLink().replace("localhost", "10.0.2.2");
        Picasso.with(context).load(imageCoffee).into(coffeeViewHolder.coffee_image);
        if(coffee.getStatus().equals("1"))
            coffeeViewHolder.outOfOrder_image.setImageResource(Common.convertOutOfOrderToImage());

        coffeeViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent coffeeDetail = new Intent(context, CoffeeDetailPage.class);
                coffeeDetail.putExtra(Common.INTENT_coffee_REF, coffee.getId()+""); // Hoặc sử dụng coffee.getId() nếu có
                context.startActivity(coffeeDetail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

}
