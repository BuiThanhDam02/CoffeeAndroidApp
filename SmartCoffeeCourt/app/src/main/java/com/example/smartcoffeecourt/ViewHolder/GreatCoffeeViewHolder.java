package com.example.smartcoffeecourt.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.R;

public class GreatCoffeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_name;
    public ImageView food_image, outOfOrder_image;
    public TextView food_price;
    public RatingBar ratingBar;


    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public GreatCoffeeViewHolder(@NonNull View itemView) {
        super(itemView);
        food_name = (TextView) itemView.findViewById(R.id.name);
        food_image = (ImageView) itemView.findViewById(R.id.food_image);
        outOfOrder_image = (ImageView) itemView.findViewById(R.id.outOfOrder_image);
        food_price = (TextView) itemView.findViewById(R.id.price);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}