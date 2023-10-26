package com.example.managerapp.SupplierSide.Menu;

import androidx.annotation.NonNull;

import com.example.managerapp.SupplierSide.Common;
import com.example.managerapp.SupplierSide.EditFood.EditFoodContract;
import com.example.managerapp.SupplierSide.Model.Coffee;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuInteractor implements MenuContract.Interactor, EditFoodContract.Interactor {
    static MenuInteractor menuInteractor;
    ArrayList<Coffee> coffeeList;
    ArrayList<String> keyList;
    MenuContract.onOperationListener menuListener;
    DatabaseReference foodReference;

    private MenuInteractor(MenuContract.onOperationListener menuListener) {
        foodReference = FirebaseDatabase.getInstance().getReference("coffee/List");
        this.menuListener = menuListener;
    }

    static public MenuInteractor getInstance(){
        return menuInteractor;
    }

    static public MenuInteractor getInstance(MenuContract.onOperationListener menuListener){
        menuInteractor = new MenuInteractor(menuListener);
        return menuInteractor;
    }

    @Override
    public void performLoadMenu(final Integer supplierID) {
        coffeeList = new ArrayList<>();
        keyList = new ArrayList<>();
        foodReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot foodSnapshot: snapshot.getChildren()){
                    Coffee coffee = foodSnapshot.getValue(Coffee.class);
                    assert coffee != null;
                    if(coffee.getSupplierID().equals(supplierID)){
                        coffeeList.add(coffee);
                        keyList.add(foodSnapshot.getKey());
                    }
                }
                menuListener.onLoadMenu(coffeeList, true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public Coffee getFood(final int position) {
       return coffeeList.get(position);
    }

    @Override
    public void performUpdateFood(final int position, final Coffee coffee) {
        String key = keyList.get(position);
        foodReference.child(key).setValue(coffee).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                boolean change = true;
                if(coffeeList.get(position).getName().equals(coffee.getName())) change = false;
                coffeeList.set(position, coffee);
                menuListener.onLoadMenu(coffeeList, change);
            }
        });
    }

    @Override
    public void performAddNewFood(final Coffee coffee) {
        final String key = foodReference.push().getKey();
        foodReference.child(key).setValue(coffee).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    performLoadMenu(Common.supplier.getSupplierID());
                    menuListener.onAddSuccess();
                }
                else{
                    menuListener.onAddFailure();
                }
            }
        });
    }

    @Override
    public void performRemoveFood(final int position) {
        String key = keyList.get(position);
        foodReference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                coffeeList.remove(position);
                keyList.remove(position);
                menuListener.onLoadMenu(coffeeList, true);
            }
        });
    }

    @Override
    public void performSearchFoods(String key) {
        ArrayList<Coffee> searchList = new ArrayList<>();
        for(Coffee coffee : coffeeList){
            if(coffee.getName().contains(key)) searchList.add(coffee);
        }
        menuListener.onLoadMenu(searchList, false);
    }

    @Override
    public void performChangeStatus(final int position) {
        String key = keyList.get(position);
        final String status;
        if(coffeeList.get(position).getStatus().equals("0")) status = "1";
        else status = "0";
        foodReference.child(key).child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                coffeeList.get(position).setStatus(status);
                menuListener.onLoadMenu(coffeeList, false);
            }
        });
    }

    @Override
    public int getMenuSize() {
        return coffeeList.size();
    }

}
