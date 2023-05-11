package com.example.smartcoffeecourt.CoffeeDetail;

import android.content.Context;

import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Rating;

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
        void loadCoffee();
        void requestRatingCoffee();
        void getCoffeeComment();
        void addCoffeeToCart(String quantity);
        void saveRating(Rating rating);
    }
}
