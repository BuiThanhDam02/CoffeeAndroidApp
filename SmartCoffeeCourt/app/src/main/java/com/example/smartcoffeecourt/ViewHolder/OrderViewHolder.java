package com.example.smartcoffeecourt.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.R;

public class OrderViewHolder extends RecyclerView.ViewHolder{

    public TextView txtOrderId, txtOrderStatus, txtTotal;
    public ImageView btnReceive;


    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtTotal = (TextView) itemView.findViewById(R.id.txtTotal);
        btnReceive = (ImageView)itemView.findViewById(R.id.btn_receive);
    }
}