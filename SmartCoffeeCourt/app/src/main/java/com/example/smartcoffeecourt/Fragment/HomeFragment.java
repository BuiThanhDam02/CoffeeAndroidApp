package com.example.smartcoffeecourt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.CoffeeDetail.CoffeeDetailPage;
import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.GreatCoffeeViewHolder;
import com.example.smartcoffeecourt.ViewHolder.StallViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    RecyclerView greatFoodRecycler, stallRecycler;
    FirebaseDatabase database;
    DatabaseReference foodList, supplierList;

    FirebaseRecyclerAdapter<Coffee, GreatCoffeeViewHolder> adapterGreatFood;
    FirebaseRecyclerAdapter<Stall, StallViewHolder> adapterStall;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food/List");
        supplierList = database.getReference("Supplier/List");
        greatFoodRecycler = root.findViewById(R.id.great_food_recycler);
        stallRecycler = root.findViewById(R.id.stall_recycler);

        greatFoodRecycler.setHasFixedSize(true);
        greatFoodRecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,true));
        stallRecycler.setHasFixedSize(true);
        stallRecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        loadGreatFoodList();
        loadStallList();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterGreatFood.startListening();
        adapterStall.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterGreatFood.stopListening();
        adapterStall.stopListening();
    }

    private void loadStallList() {
        FirebaseRecyclerOptions<Stall> options = new FirebaseRecyclerOptions.Builder<Stall>().setQuery(supplierList.orderByChild("supplierID"), Stall.class).build();
        adapterStall = new FirebaseRecyclerAdapter<Stall, StallViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StallViewHolder stallViewHolder, int i, final Stall stall) {
                stallViewHolder.txtStall.setText(stall.getName());
                if(!stall.getImage().isEmpty()) Picasso.with(getContext()).load(stall.getImage()).into(stallViewHolder.imgStall);
                stallViewHolder.txtNumber.setText(stall.getSupplierID().toString());
                stallViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        CoffeeFragment coffeeFragment = new CoffeeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt(Common.CHOICE_STALL, stall.getSupplierID());
                        coffeeFragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, coffeeFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }

            @NonNull
            @Override
            public StallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stall_item, parent, false);
                return new StallViewHolder(itemView);
            }
        };
        adapterStall.notifyDataSetChanged();
        stallRecycler.setAdapter(adapterStall);
    }

    private void loadGreatFoodList() {
        FirebaseRecyclerOptions<Coffee> options = new FirebaseRecyclerOptions.Builder<Coffee>()
                .setQuery(foodList.orderByChild("star").limitToLast(5), Coffee.class).build();

        adapterGreatFood = new FirebaseRecyclerAdapter<Coffee, GreatCoffeeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GreatCoffeeViewHolder greatCoffeeViewHolder, int i, @NonNull Coffee coffee) {
                if(coffee.getStatus().equals("1"))
                    greatCoffeeViewHolder.outOfOrder_image.setImageResource(Common.convertOutOfOrderToImage());
                else greatCoffeeViewHolder.outOfOrder_image.setImageResource(0);
                greatCoffeeViewHolder.ratingBar.setRating(Float.parseFloat(coffee.getStar()));
                greatCoffeeViewHolder.food_name.setText(coffee.getName());
                greatCoffeeViewHolder.food_price.setText(Common.convertPriceToVND(coffee.getPrice()));
                Picasso.with(getContext()).load(coffee.getImage()).into(greatCoffeeViewHolder.food_image);

                greatCoffeeViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent foodDetail = new Intent(getContext(), CoffeeDetailPage.class);
                        foodDetail.putExtra(Common.INTENT_coffee_REF, adapterGreatFood.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });
            }

            @NonNull
            @Override
            public GreatCoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                greatFoodRecycler.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left));
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.great_food_item, parent, false);
                return new GreatCoffeeViewHolder(itemView);
            }
        };
        adapterGreatFood.notifyDataSetChanged();
        greatFoodRecycler.setAdapter(adapterGreatFood);
    }

}