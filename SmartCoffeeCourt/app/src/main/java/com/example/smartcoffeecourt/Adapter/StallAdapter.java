package com.example.smartcoffeecourt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Fragment.CoffeeFragment;
import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.OrderDetail;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.StallViewHolder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StallAdapter extends RecyclerView.Adapter<StallAdapter.StallViewHolderDung> {

    private Context context;
    private List<Stall> stallList;

    private FragmentManager fragmentManager;

    public StallAdapter(Context context, List<Stall> stallList, FragmentManager fragmentManager) {
        this.context = context;
        this.stallList = stallList;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public StallViewHolderDung onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StallViewHolderDung(LayoutInflater.from(parent.getContext()).inflate(R.layout.stall_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StallViewHolderDung holder, int position) {
        Stall stall = stallList.get(position);

        //loadImage(holder.imgStall,"http://192.168.212.147:8080/images/sup_image/" + stall.getImage());
        Picasso.with(context)
                .load("http://172.172.10.9:8080/images/sup_image/" + stall.getImage())
                .into(holder.imgStall);

        holder.txtStall.setText(stall.getName());
        holder.txtNumber.setText(String.valueOf(stall.getSupplierID()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoffeeFragment coffeeFragment = new CoffeeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Common.CHOICE_STALL, stall.getSupplierID());
                coffeeFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, coffeeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stallList.size();
    }

    private void loadImage(ImageView imageView, String path) {
        try {
            // get input stream
            InputStream ims = context.getAssets().open(path);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imageView.setImageDrawable(d);
            ims.close();
        } catch (IOException ex) {
            return;
        }
    }

    public class StallViewHolderDung extends RecyclerView.ViewHolder {

        public TextView txtStall;
        public ImageView imgStall;
        public TextView txtNumber;

        public StallViewHolderDung(@NonNull View itemView) {
            super(itemView);
            txtStall =  itemView.findViewById(R.id.txtName);
            imgStall = (ImageView) itemView.findViewById(R.id.imgStall);
            txtNumber = itemView.findViewById(R.id.txtNumber);
        }
    }
}