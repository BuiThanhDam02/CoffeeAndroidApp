package com.example.smartcoffeecourt.ApiService;

import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Model.OrderDetailModel;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/auth/register")
    Call<User> signUp(@Body UserRegistrationRequest user);
    @GET("coffee/all")
    Call<List<Coffee>> getCoffee();

    @GET("supplier/all")
    Call<List<Stall>> getAllStall();

    @GET("coffee/bySupplierId")
    Call<List<Coffee>> getCoffeesBySupplier(@Query("supplierId") int supplierId);

    @GET("supplier/getAll")
    Call<List<Stall>> getAllStallDung();

    @GET("/order/getByUser")
    Call<List<Order>> getAllOrderByUserId(@Query("idUser") int idUser);

    @GET("/order/detail/getByOrderId")
    Call<OrderDetailModel> getDetailOrder(@Query("id") Long id);
    @GET("coffee/search")
    Call<List<Coffee>> searchByName(@Query("name") String name);

    @GET("coffee/getGreatCoffee")
    Call<List<Coffee>> getGreatCoffee();
}
