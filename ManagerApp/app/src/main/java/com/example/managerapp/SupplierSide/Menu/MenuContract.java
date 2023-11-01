package com.example.managerapp.SupplierSide.Menu;

import com.example.managerapp.SupplierSide.Model.Coffee;

import java.util.ArrayList;

public interface MenuContract {
    interface Presenter{
        void loadMenu();
        void removeFood(int position);
        void changeFoodStatus(int position);
        void loadSearchFoods(String key);
        void requestAddFood();
    }
    interface View {
        void showLimitExcessDialog();
        void showAddFood();
        void showToast(String message);
        void showMenu(ArrayList<Coffee> menu);
        void showConnectionError();
        void setSuggestionList(ArrayList<String> foodNameList);
        void onProcessStart();
        void onProcessEnd();
    }
    interface Interactor{
        void performLoadMenu(Integer supplierID);
        void performRemoveFood(int position);
        void performSearchFoods(String key);
        void performChangeStatus(int position);
        int getMenuSize();
    }
    interface onOperationListener{
        void onAddSuccess();
        void onAddFailure();
        void onLoadMenu(ArrayList<Coffee> coffeeList, boolean change);
    }
}
