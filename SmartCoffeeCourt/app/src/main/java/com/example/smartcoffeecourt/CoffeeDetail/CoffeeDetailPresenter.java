package com.example.smartcoffeecourt.CoffeeDetail;

import androidx.annotation.NonNull;

import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Database.Database;
import com.example.smartcoffeecourt.Model.CartItem;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Rating;
import com.example.smartcoffeecourt.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.paperdb.Paper;

public class CoffeeDetailPresenter implements CoffeeDetailContract.Presenter {
    DatabaseReference coffeeReference, ratingReference,likeReference;
    CoffeeDetailContract.View coffeeView;
    String coffeeRef;
    Coffee coffee;

    boolean isLiked;
 public CoffeeDetailPresenter(CoffeeDetailContract.View coffeeView, String coffeeRef){
        this.coffeeView = coffeeView;
        this.coffeeRef = coffeeRef;
        coffeeReference = FirebaseDatabase.getInstance().getReference("coffee/List");
        ratingReference = FirebaseDatabase.getInstance().getReference("Rating/" + coffeeRef + "/List");
        if (Paper.book().read(Common.USER_UID)==null){
            likeReference = FirebaseDatabase.getInstance().getReference("Like/"+Common.userId+"/List");
        }else{
            likeReference = FirebaseDatabase.getInstance().getReference("Like/"+Paper.book().read(Common.USER_UID)+"/List");
        }

    }

    @Override
    public void getCoffeeComment() {
        coffeeView.showCommentPage(coffeeRef);
    }

    @Override
    public void addCoffeeToCart(String quantity) {
        if(coffee.getStatus().equals("0")) {
            new Database(coffeeView.getContext()).addToCart(new CartItem(coffee.getName(),
                    coffee.getPrice(), quantity,
//                    coffee.getDiscount()
                    ""
            ), coffee.getSupplier().getSupplierID());
            coffeeView.showToast("Món ăn đã được thêm vào giỏ hàng");
        }
        else coffeeView.showToast("Món ăn đã hết hàng");
    }

    @Override
    public void saveRating(Rating rating) {
        ratingReference.child(Common.user.getName()).setValue(rating);
        coffeeView.showToast("Đánh giá của bạn đã được lưu lại. Cảm ơn bạn rất nhiều.");
    }
    @Override
    public void likeCoffee() {

                if(isLiked == true) {

                    likeReference.child(coffeeRef).removeValue();
                    isLiked = false;
                    coffeeView.showToast("Bạn đã xóa yêu thích sản phẩm");

                }
                else {
                    likeReference.child(coffeeRef).setValue(coffee);
                    isLiked = true;
                    coffeeView.showToast("Bạn đã lưu yêu thích sản phẩm");
                };


    }
    @Override
    public boolean getIsLikeCoffee(){
     return this.isLiked;
    }
    @Override
    public void checkLikeCoffee() {

        likeReference.child(coffeeRef).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Coffee checkCoffee = snapshot.getValue(Coffee.class);
                if(checkCoffee != null) {
                   isLiked = true;
                }
                else {

                   isLiked = false;
                };
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void loadCoffee() {
        coffeeReference.child(coffeeRef).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                coffee = snapshot.getValue(Coffee.class);
                if(coffee != null){

                    coffeeView.showCoffeeDetail(coffee);
                }
                else coffeeView.closeView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void requestRatingCoffee() {
        ArrayList<String> levelList = new ArrayList<>();
        levelList.add("Rất tệ");
        levelList.add("Không ngon");
        levelList.add("Khá OK");
        levelList.add("Rất ngon");
        levelList.add("Xuất sắc");
        coffeeView.showRatingDialog(levelList);
    }
}
