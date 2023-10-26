package com.example.managerapp.SupplierSide.EditFood;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.managerapp.SupplierSide.Common;
import com.example.managerapp.SupplierSide.Menu.MenuInteractor;
import com.example.managerapp.SupplierSide.Model.Coffee;
import com.example.managerapp.SupplierSide.Model.MenuItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class EditFoodPresenter implements EditFoodContract.Presenter {
    EditFoodContract.View editFoodView;
    EditFoodContract.Interactor editFoodInteractor;
    StorageReference storage;
    int position;
    Coffee coffee;
    Uri localUri;

    public EditFoodPresenter(EditFoodContract.View editFoodView) {
        this.editFoodView = editFoodView;
        editFoodInteractor = MenuInteractor.getInstance();
        storage = FirebaseStorage.getInstance().getReference();
        position = -1;
        coffee = new Coffee();
    }

    @Override
    public void loadFoodDetail(int position) {
        if(position > -1){
            this.position = position;
            coffee = editFoodInteractor.getFood(position);
            editFoodView.showFoodDetail(coffee);
        }
    }

    @Override
    public void saveFood(MenuItem menuItem) {
        if(checkInput(menuItem)){
            coffee.setMenuItem(menuItem);
            if(localUri != null){
                editFoodView.showProgress("Đang tải ảnh...");
                String imageName = UUID.randomUUID().toString();
                final StorageReference imageFolder = storage.child("food-" + imageName);
                imageFolder.putFile(localUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        coffee.setImage(uri.toString());
                                        if(position == -1){
                                            coffee.setSupplierID(Common.supplier.getSupplierID());
                                            editFoodInteractor.performAddNewFood(coffee);
                                        }
                                        else editFoodInteractor.performUpdateFood(position, coffee);
                                        editFoodView.stopProgress();
                                        editFoodView.closeView();
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                editFoodView.stopProgress();
                                editFoodView.showConnectionError();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                                int progress = (int)(100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                editFoodView.showProgress("Ảnh đã tải " + progress + "%");
                            }
                        });
            }
            else {
                if(position == -1) editFoodView.showInvalidMessage("Hãy thêm ảnh món ăn");
                else {
                    editFoodView.showProgress("Đang cập nhật....");
                    editFoodInteractor.performUpdateFood(position, coffee);
                    editFoodView.stopProgress();
                    editFoodView.closeView();
                }

            }
        }
    }

    private boolean checkInput(MenuItem menuItem) {
        if(menuItem.getName().isEmpty()) {
            editFoodView.showInvalidMessage("Hãy nhập tên món ăn");
            return false;
        }
        if(menuItem.getPrice().isEmpty()) {
            editFoodView.showInvalidMessage("Hãy nhập giá món ăn");
            return false;
        }
        return true;
    }

    @Override
    public void saveLocalUri(Uri uri) {
        this.localUri = uri;
        editFoodView.showFoodImage(uri);
    }
}
