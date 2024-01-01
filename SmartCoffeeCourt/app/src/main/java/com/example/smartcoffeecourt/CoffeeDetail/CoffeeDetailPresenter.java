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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Call<List<Coffee>> call = Network.getInstance().create(ApiService.class).getCoffeeComments(coffeeRef);
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                if (response.isSuccessful()){
                    List<Coffee> coffeeList = response.body();
                    if(coffeeList != null && !coffeeList.isEmpty()){
                        coffeeView.showCommentPage(coffeeRef);
                    } else {
                        coffeeView.showToast("Không có bình luận nào");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                System.out.println("Wrong network");
            }
        });
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
        Call<Coffee> call = Network.getInstance().create(ApiService.class).saveRating(rating);
        call.enqueue(new Callback<Coffee>() {
            @Override
            public void onResponse(Call<Coffee> call, Response<Coffee> response) {
                if(response.isSuccessful()){
                    coffeeView.showToast("Đánh giá của bạn đã được lưu lại. Cảm ơn bạn rất nhiều.");
                }
            }

            @Override
            public void onFailure(Call<Coffee> call, Throwable t) {
                System.out.println("Wrong network");
            }
        });
    }
    @Override
    public void likeCoffee() {
        int userId = Common.user.getId();
        int coffeeId = Integer.parseInt(coffeeRef);
         Call<String> call = Network.getInstance().create(ApiService.class).toggleLike(userId, coffeeId);
         call.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 if(response.isSuccessful()){
                     assert response.body() != null;
                     if(response.body().equals("Like removed successfully")){
                         isLiked = false;
                         coffeeView.showToast("Bạn đã xóa yêu thích sản phẩm");
                     } else if (response.body().equals("Like added successfully")) {
                         isLiked = true;
                         coffeeView.showToast("Bạn đã thêm yêu thích sản phẩm");
                     } else {
                         coffeeView.showToast("Đã xảy ra lỗi");
                     }
                 }
             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 System.out.println("Wrong network");
             }
         });
    }
    @Override
    public boolean getIsLikeCoffee(){
     return this.isLiked;
    }
    @Override
    public void checkLikeCoffee() {
        int userId = Common.user.getId();
        int coffeeId = Integer.parseInt(coffeeRef);
        Call<Like> call = Network.getInstance().create(ApiService.class).checkLike(userId, coffeeId);
     call.enqueue(new Callback<Like>() {
         @Override
         public void onResponse(Call<Like> call, Response<Like> response) {
             if(response.isSuccessful()){
                 Like checkLike = response.body();
                 if(checkLike!=null){
                     isLiked = true;
                 } else{
                     isLiked = false;
                 }
             }

         }

         @Override
         public void onFailure(Call<Like> call, Throwable t) {
             System.out.println("Wrong network");
         }
     });

    }
    @Override
    public void loadCoffee() {
     Call<Coffee> call = Network.getInstance().create(ApiService.class).getCoffeeById(Long.parseLong(coffeeRef));
     call.enqueue(new Callback<Coffee>() {
         @Override
         public void onResponse(Call<Coffee> call, Response<Coffee> response) {
             if(response.isSuccessful()) {
                 coffee = response.body();
                 assert coffee != null;
                 coffeeView.showCoffeeDetail(coffee);
             }
         }

         @Override
         public void onFailure(Call<Coffee> call, Throwable t) {
//                coffeeView.closeView();
             System.out.println("Loi goi api");
         }
     });
//        coffeeReference.child(coffeeRef).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                coffee = snapshot.getValue(Coffee.class);
//                if(coffee != null){
//
//                    coffeeView.showCoffeeDetail(coffee);
//                }
//                else coffeeView.closeView();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        System.out.println("Đây là gì nữa: " + coffeeRef);
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
