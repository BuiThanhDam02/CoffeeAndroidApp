package com.example.managerapp.SupplierSide.EditFood;

import android.net.Uri;

import com.example.managerapp.SupplierSide.Model.Coffee;
import com.example.managerapp.SupplierSide.Model.MenuItem;

public interface EditFoodContract {
    interface View {
        void showInvalidMessage(String message);
        void showFoodDetail(Coffee coffee);
        void showFoodImage(Uri uri);
        void showProgress(String message);
        void stopProgress();
        void showConnectionError();
        void closeView();
    }
    interface Presenter{
        void loadFoodDetail(int position);
        void saveFood(MenuItem menuItem);
        void saveLocalUri(Uri uri);
    }
    interface Interactor{
        Coffee getFood(int position);
        void performUpdateFood(int position, Coffee coffee);
        void performAddNewFood(Coffee coffee);
    }
}
