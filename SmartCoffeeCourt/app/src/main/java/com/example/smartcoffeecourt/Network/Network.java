package com.example.smartcoffeecourt.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public static String URL = "http://10.0.2.2";
    public static int PORT = 8080;
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static Retrofit getInstance() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL + ":" + PORT).addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build());
        return builder.build();
    }

    public static String getImage(String image) {
        return URL+":"+PORT+image;
    }
}
