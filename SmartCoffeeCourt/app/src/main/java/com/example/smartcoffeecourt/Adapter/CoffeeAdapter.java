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
import com.example.smartcoffeecourt.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

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
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        Coffee coffee = coffeeList.get(position);

        if (coffee.getStatus() != null && coffee.getStatus().equals("1")) {
            holder.outOfOrderImage.setImageResource(Common.convertOutOfOrderToImage());
        }

        holder.coffeeName.setText(coffee.getName());
        holder.coffeePrice.setText(Common.convertPriceToVND(coffee.getPrice()));
        holder.coffeeSupplier.setText(String.format("Stall %s", coffee.getSupplierID()));

        Picasso.with(context).load(coffee.getImage()).into(holder.coffeeImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý khi một mục trong danh sách được nhấp
                // Ví dụ: Chuyển đến trang chi tiết cà phê
                Intent coffeeDetail = new Intent(context, CoffeeDetailPage.class);
                coffeeDetail.putExtra(Common.INTENT_coffee_REF, coffee.getSupplierID()); // Sửa lại theo id hoặc thông tin cần thiết
                context.startActivity(coffeeDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public class CoffeeViewHolder extends RecyclerView.ViewHolder {

        ImageView outOfOrderImage;
        TextView coffeeName, coffeePrice, coffeeSupplier;
        ImageView coffeeImage;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);

            outOfOrderImage = itemView.findViewById(R.id.food_image);
            coffeeName = itemView.findViewById(R.id.name);
            coffeePrice = itemView.findViewById(R.id.price);
            coffeeImage = itemView.findViewById(R.id.coffee_image);
        }
    }
}
