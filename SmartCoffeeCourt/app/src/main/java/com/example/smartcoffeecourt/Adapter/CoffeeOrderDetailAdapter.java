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
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.CoffeeOrderDetail;
import com.example.smartcoffeecourt.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoffeeOrderDetailAdapter extends RecyclerView.Adapter<CoffeeOrderDetailAdapter.CoffeeViewHolder> {

    private List<CoffeeOrderDetail> coffeeList;
    private Context context;

    public CoffeeOrderDetailAdapter(List<CoffeeOrderDetail> coffeeList, Context context) {
        this.coffeeList = coffeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public CoffeeOrderDetailAdapter.CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_item_order_detail, parent, false);
        return new CoffeeOrderDetailAdapter.CoffeeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CoffeeOrderDetailAdapter.CoffeeViewHolder holder, int position) {
        CoffeeOrderDetail coffee = coffeeList.get(position);

        holder.coffeeName.setText(coffee.getName());
        holder.coffeePrice.setText(Common.convertPriceToVND(coffee.getPrice().substring(0,coffee.getPrice().indexOf("."))));
        holder.coffeeSupplier.setText(coffee.getSupplierName());
        Picasso.with(context).load("http://172.172.10.9:8080/images/coffee_image/" + coffee.getImageLink()).into(holder.coffeeImage);
        holder.coffeeQuantity.setText("x " + String.valueOf(coffee.getQuantity()));

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý khi một mục trong danh sách được nhấp
                // Ví dụ: Chuyển đến trang chi tiết cà phê
                Intent coffeeDetail = new Intent(context, CoffeeDetailPage.class);
                coffeeDetail.putExtra(Common.INTENT_coffee_REF, coffee.getSupplierID()); // Sửa lại theo id hoặc thông tin cần thiết
                context.startActivity(coffeeDetail);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public class CoffeeViewHolder extends RecyclerView.ViewHolder {

        TextView coffeeName, coffeePrice, coffeeSupplier,coffeeQuantity;
        ImageView coffeeImage;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeQuantity = itemView.findViewById(R.id.coffee_quantity);
            coffeeSupplier = itemView.findViewById(R.id.txtSupplier);
            coffeeName = itemView.findViewById(R.id.name);
            coffeePrice = itemView.findViewById(R.id.price);
            coffeeImage = itemView.findViewById(R.id.coffee_image);
        }
    }
}
