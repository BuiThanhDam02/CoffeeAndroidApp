package com.example.smartcoffeecourt.CoffeeDetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Model.Rating;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.CommentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentPage extends AppCompatActivity  {

    RecyclerView recyclerView;
    CommentAdapter adapter;
    String coffeeRef;

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        recyclerView = findViewById(R.id.recyclerComment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null) {
            coffeeRef = getIntent().getStringExtra(Common.INTENT_coffee_REF);
        }
        if(coffeeRef != null && !coffeeRef.isEmpty()) {
            Call<List<Rating>> call = Network.getInstance().create(ApiService.class).getCoffeeComments(Long.parseLong(coffeeRef));
            call.enqueue(new Callback<List<Rating>>() {
                @Override
                public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                    if (response.isSuccessful()) {
                        List<Rating> ratings = response.body();
                        adapter = new CommentAdapter(ratings);
                        recyclerView.setAdapter(adapter);
                        System.out.println("Rating: " + ratings);
                    }
                }

                @Override
                public void onFailure(Call<List<Rating>> call, Throwable t) {
                    System.out.println("Loix");
                }
            });
        }
        }
    }
    class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
        private List<Rating> ratings;

        public CommentAdapter(List<Rating> ratings) {
            this.ratings = ratings;
        }

        @NonNull
        @Override
        public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
            return new CommentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
            Rating rating = ratings.get(position);
            holder.ratingBarDetail.setRating(Float.parseFloat(String.valueOf(rating.getStar())));
            holder.txtUserName.setText(rating.getUser().getName());
            holder.txtComment.setText(rating.getContent());
        }

        @Override
        public int getItemCount() {
            return ratings.size();
        }
    }
