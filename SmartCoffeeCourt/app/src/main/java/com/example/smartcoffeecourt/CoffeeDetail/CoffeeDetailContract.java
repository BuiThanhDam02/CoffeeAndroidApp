package com.example.smartcoffeecourt.CoffeeDetail;

import android.content.Context;

import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Rating;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public interface CoffeeDetailContract {
    interface View{
        void showCoffeeDetail(Coffee coffee);
        void showRatingDialog(ArrayList<String> levelList);
        void showCommentPage(String coffeeRef);
        void showToast(String message);
        void closeView();
        Context getContext();
    }
    interface Presenter{
        void loadCoffee(FloatingActionButton btnLike);
        void requestRatingCoffee();
        void getCoffeeComment();
        void addCoffeeToCart(String quantity);
        void saveRating(Rating rating);
        void likeCoffee(FloatingActionButton btnLike);
        void checkLikeCoffee(FloatingActionButton btnLike);
        boolean getIsLikeCoffee();
    }
}
