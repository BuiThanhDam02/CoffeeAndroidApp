package com.example.smartcoffeecourt.ApiService;

import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Like;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Model.OrderDetailModel;
import com.example.smartcoffeecourt.Model.Rating;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/api/auth/register")
    Call<User> signUp(@Body UserRegistrationRequest user);

    @POST("/api/auth/login")
    Call<AuthenticationResponse> signIn(@Body UserLoginRequest user);

    @GET("/api/coffee/all")
    Call<List<Coffee>> getAllCoffee();

    @GET("/api/coffee/get/{id}")
    Call<Coffee> getCoffeeById(@Path("id") Long id);

    @POST("/api/coffee/rating")
    Call<Coffee> saveRating(@Body Rating rating);

    @GET("/api/comments/{id}")
    Call<List<Coffee>> getCoffeeComments(@Path("id")String coffeeId);
    @POST("/api/likes/toggleLike")
    Call<String> toggleLike(@Query("user_id") int userId, @Query("coffee_id") int coffeeId);

    @GET("/api/likes/checkLike")
    Call<Like> checkLike(@Query("user_id") int userId, @Query("coffee_id") int coffeeId);

    @GET("/api/supplier/all")
    Call<List<Stall>> getAllStall();

    @GET("/api/coffee/bySupplierId")
    Call<List<Coffee>> getCoffeesBySupplier(@Query("supplierId") int supplierId);

    @GET("/api/supplier/getAll")
    Call<List<Stall>> getAllStallDung();

    @GET("/api/order/getByUser")
    Call<List<Order>> getAllOrderByUserId(@Query("idUser") int idUser);

    @GET("/api/order/detail/getByOrderId")
    Call<OrderDetailModel> getDetailOrder(@Query("id") Long id);
    @GET("/api/coffee/search")
    Call<List<Coffee>> searchByName(@Query("name") String name);

    @POST("/api/order/checkout")
    Call<Order> checkout(@Body Order order);

    @GET("/api/coffee/getGreatCoffee")
    Call<List<Coffee>> getGreatCoffee();
}
