package com.example.smartcoffeecourt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Model.Rating;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.CommentViewHolder;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private List<Rating> ratings;

    public RatingAdapter(List<Rating> ratings) {
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
        holder.ratingBarDetail.setRating(Float.parseFloat(rating.getRateValue()));
        holder.txtComment.setText(rating.getComment());
    }

    @Override
    public int getItemCount() {
        return ratings.size();
    }
}
