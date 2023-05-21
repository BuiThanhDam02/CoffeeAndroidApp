package com.example.smartcoffeecourt.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.R;

public class CoffeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView coffee_name;
    public ImageView coffee_image, outOfOrder_image;
    public TextView coffee_price, coffee_supplier;


    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CoffeeViewHolder(@NonNull View itemView) {
        super(itemView);
        coffee_name = (TextView)itemView.findViewById(R.id.name);
        coffee_image = (ImageView)itemView.findViewById(R.id.coffee_image);
        coffee_price = (TextView)itemView.findViewById(R.id.price);
        coffee_supplier = itemView.findViewById(R.id.txtSupplier);
        outOfOrder_image = (ImageView) itemView.findViewById(R.id.outOfOrder_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}
