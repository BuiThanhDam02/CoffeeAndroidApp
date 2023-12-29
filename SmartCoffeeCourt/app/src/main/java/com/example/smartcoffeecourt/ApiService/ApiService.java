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
import retrofit2.http.Query;

public interface ApiService {
    @POST("/api/auth/register")
    Call<User> signUp(@Body UserRegistrationRequest user);
    @POST("/api/auth/login")
    Call<AuthenticationResponse> signIn(@Body UserLoginRequest user);

    @GET("/api/coffee/all")
    Call<List<Coffee>> getAllCoffee();

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
}
