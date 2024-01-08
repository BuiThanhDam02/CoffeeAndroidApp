package com.example.smartcoffeecourt.Adapter;

import android.annotation.SuppressLint;
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
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.OrderDetail;
import com.example.smartcoffeecourt.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        System.out.println("Coi thử: " + orderList.get(position));
        holder.idOrder.setText(String.valueOf(orderList.get(position).getId()));
        holder.statusOrder.setText(orderList.get(position).getStatus());
        holder.totalPrice.setText(Common.convertPriceToVND(Float.valueOf(orderList.get(position).getTotal())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderDetail.class);
                intent.putExtra("id", String.valueOf(orderList.get(position).getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView idOrder,statusOrder,totalPrice;
        ImageView btnReceive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idOrder = (TextView)itemView.findViewById(R.id.order_id);
            statusOrder = (TextView)itemView.findViewById(R.id.order_status);
            totalPrice = (TextView)itemView.findViewById(R.id.txtTotal);
            btnReceive = (ImageView)itemView.findViewById(R.id.btn_receive);
        }

        @Override
        public void onClick(View view) {

        }
    }
}