package com.example.smartcoffeecourt.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.OrderAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Model.Stall;
import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;


public class OrderFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Order> orderList;
    OrderAdapter adapterOrder;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);


        recyclerView = root.findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        try {
            loadOrder(Common.user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return root;
    }



    private void loadOrder(Integer userId) throws Exception {
        //orderList = Network.getInstance().create(ApiService.class).getAllOrderByUserIdDung(userId);


       /* String apiUrl = "http://localhost:8080/api/order/getByUser";
        orderList = new ArrayList<>();

        String dataRq = "{ id:" + userId +"}";
        String dataRp = getDataFromUrl(apiUrl,"GET",dataRq);
        ObjectMapper mapper = new ObjectMapper();
        orderList = mapper.readValue(dataRp, new TypeReference<List<Order>>() {});
        System.out.println("orderSize:" + orderList.size());
        adapterOrder = new OrderAdapter(getActivity(),orderList);
        adapterOrder.notifyDataSetChanged();
        recyclerView.setAdapter(adapterOrder);*/
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private static String getDataFromUrl(String urlAPI,String type, String dataRq) throws IOException {
        URL url = new URL(urlAPI);
        StringBuilder dataRp = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(type);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(dataRq.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        if (conn.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                dataRp.append(inputLine);
            }
            in.close();
            System.out.println("Successfully sent JSON data to API.");
        } else {
            System.out.println("Error: Failed to send JSON data to API.");
        }
        conn.disconnect();
        return dataRp.toString();
    }


}