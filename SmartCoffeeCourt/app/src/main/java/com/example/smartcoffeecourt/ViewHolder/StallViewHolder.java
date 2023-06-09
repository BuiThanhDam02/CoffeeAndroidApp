package com.example.smartcoffeecourt.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.R;

public class StallViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtStall;
    public ImageView imgStall;
    public TextView txtNumber;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public StallViewHolder(@NonNull View itemView) {
        super(itemView);
        txtStall = (TextView)itemView.findViewById(R.id.txtName);
        imgStall = (ImageView)itemView.findViewById(R.id.imgStall);
        txtNumber = itemView.findViewById(R.id.txtNumber);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}
