package com.example.smartcoffeecourt.CoffeeDetail;

import androidx.annotation.NonNull;

import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Database.Database;
import com.example.smartcoffeecourt.Model.CartItem;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Like;
import com.example.smartcoffeecourt.Model.Rating;
import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.Service.LikeResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoffeeDetailPresenter implements CoffeeDetailContract.Presenter {
    CoffeeDetailContract.View coffeeView;
    String coffeeRef;
    Coffee coffee;
    boolean isLiked;

 public CoffeeDetailPresenter(CoffeeDetailContract.View coffeeView, String coffeeRef){
        this.coffeeView = coffeeView;
        this.coffeeRef = coffeeRef;


    }

    @Override
    public void getCoffeeComment() {
        coffeeView.showCommentPage(coffeeRef);
    }

    @Override
    public void addCoffeeToCart(String quantity) {
        System.out.println("Check Coffee: " + coffee);
        if(coffee.getStatus().equals("0")) {
            new Database(coffeeView.getContext()).addToCart(new CartItem(Long.parseLong(coffee.getId()+"") ,coffee.getName(),
                    coffee.getPrice(), quantity,
//                    coffee.getDiscount()
                    "0"
            ), coffee.getSupplier().getSupplierID());
            coffeeView.showToast("Món ăn đã được thêm vào giỏ hàng");
        }
        else coffeeView.showToast("Món ăn đã hết hàng");
    }

    @Override
    public void saveRating(Rating rating) {
        Call<Void> call = Network.getInstance().create(ApiService.class).saveComment(rating);
      call.enqueue(new Callback<Void>() {
          @Override
          public void onResponse(Call<Void> call, Response<Void> response) {
              if(response.isSuccessful()) {
                  coffeeView.showToast("Đánh giá của bạn đã được lưu lại. Cảm ơn bạn rất nhiều.");
              }
          }

          @Override
          public void onFailure(Call<Void> call, Throwable t) {
              t.printStackTrace();
              coffeeView.showToast("Đã xảy ra lỗi!");
          }
      });
    }
    @Override
    public void likeCoffee(FloatingActionButton btnLike) {
        int userId = Common.user.getId();
        int coffeeId = Integer.parseInt(coffeeRef);
        Call<LikeResponse> call = Network.getInstance().create(ApiService.class).toggleLike(new Like(userId, coffeeId));
        call.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                if(response.isSuccessful()){
                    LikeResponse likeResponse = response.body();
                    if(likeResponse.getMessage().equals("Like removed successfully")){
                        isLiked = false;
                        btnLike.setImageDrawable(btnLike.getResources().getDrawable(R.drawable.baseline_favorite_24));
                        coffeeView.showToast("Bạn đã xóa yêu thích sản phẩm");
                    }
                    if(likeResponse.getMessage().equals("Like added successfully")){
                        isLiked = true;
                        btnLike.setImageDrawable(btnLike.getResources().getDrawable(R.drawable.baseline_favorite_red_24));
                        coffeeView.showToast("Bạn đã thêm yêu thích sản phẩm");
                    }
                }
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    @Override
    public boolean getIsLikeCoffee(){
     return this.isLiked;
    }
    @Override
    public void checkLikeCoffee(FloatingActionButton btnLike) {
        int userId = Common.user.getId();
        int coffeeId = Integer.parseInt(coffeeRef);
        Call<LikeResponse> call = Network.getInstance().create(ApiService.class).checkLike(userId, coffeeId);
        call.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                if (response.isSuccessful()) {
                    LikeResponse likeResponse = response.body();
                    System.out.println("LikeR: " + likeResponse);
                    if (likeResponse.getMessage().equals("Liked")) {
                        btnLike.setImageDrawable(btnLike.getResources().getDrawable(R.drawable.baseline_favorite_red_24));
                    } else {
                        btnLike.setImageDrawable(btnLike.getResources().getDrawable(R.drawable.baseline_favorite_24));
                    }
                }
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadCoffee(FloatingActionButton btnLike) {
     Call<Coffee> call = Network.getInstance().create(ApiService.class).getCoffeeById(Long.parseLong(coffeeRef));
     call.enqueue(new Callback<Coffee>() {
         @Override
         public void onResponse(Call<Coffee> call, Response<Coffee> response) {
             if(response.isSuccessful()) {
                 coffee = response.body();
                 assert coffee != null;
                 checkLikeCoffee(btnLike);
                 coffeeView.showCoffeeDetail(coffee);
             }
         }

         @Override
         public void onFailure(Call<Coffee> call, Throwable t) {
                coffeeView.closeView();
             System.out.println("Loi goi api");
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
