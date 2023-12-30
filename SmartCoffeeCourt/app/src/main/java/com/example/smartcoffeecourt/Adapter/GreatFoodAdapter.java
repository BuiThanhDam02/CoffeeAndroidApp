package com.example.smartcoffeecourt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.CoffeeDetail.CoffeeDetailPage;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.CoffeeViewHolder;
import com.example.smartcoffeecourt.ViewHolder.GreatCoffeeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GreatFoodAdapter extends RecyclerView.Adapter<GreatCoffeeViewHolder> {

    private List<Coffee> coffeeList;
    private Context context;

    public GreatFoodAdapter(List<Coffee> coffeeList, Context context) {
        this.coffeeList = coffeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public GreatCoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.great_food_item, parent, false);
        return new GreatCoffeeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull GreatCoffeeViewHolder greatCoffeeViewHolder, int position) {

        if(coffeeList.get(position).getStatus().equals("1"))
            greatCoffeeViewHolder.outOfOrder_image.setImageResource(Common.convertOutOfOrderToImage());
        else greatCoffeeViewHolder.outOfOrder_image.setImageResource(0);
        greatCoffeeViewHolder.ratingBar.setRating(Float.parseFloat(String.valueOf(coffeeList.get(position).getStar())));
        greatCoffeeViewHolder.food_name.setText(coffeeList.get(position).getName());
        greatCoffeeViewHolder.food_price.setText(Common.convertPriceToVND(Float.parseFloat(coffeeList.get(position).getPrice())));
        Picasso.with(context).load(coffeeList.get(position).getImageLink().replace("localhost", "192.168.1.6")).into(greatCoffeeViewHolder.food_image);

        greatCoffeeViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent coffeeDetail = new Intent(context, CoffeeDetailPage.class);
                coffeeDetail.putExtra(Common.INTENT_coffee_REF, position); // Hoặc sử dụng coffee.getId() nếu có
                context.startActivity(coffeeDetail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }
}
