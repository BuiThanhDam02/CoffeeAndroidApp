package com.example.smartcoffeecourt.CoffeeDetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartcoffeecourt.Adapter.RatingAdapter;
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

public class CommentPage extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ratingReference;
    RatingAdapter adapter;
    String foodRef;

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

        if(getIntent() != null)
            foodRef = getIntent().getStringExtra(Common.INTENT_coffee_REF);
        if(foodRef != null && !foodRef.isEmpty()) {
            Call<List<Rating>> call = Network.getInstance().create(ApiService.class).getCommentsById(foodRef);
            call.enqueue(new Callback<List<Rating>>() {
                @Override
                public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                    if(response.isSuccessful()){
                        adapter = new RatingAdapter(response.body());
                        recyclerView.setAdapter(adapter);
                    }else{
                        Toast.makeText(CommentPage.this, "Error loading ratings", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Rating>> call, Throwable t) {
                    System.out.println("Wrong network");
                }
            });
        }
    }
}