package com.example.smartcoffeecourt.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.CoffeeAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.CoffeeDetail.CoffeeDetailPage;
import com.example.smartcoffeecourt.Interface.ItemClickListener;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.ViewHolder.CoffeeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoffeeFragment extends Fragment {


    RecyclerView recyclerCoffee;

    FirebaseDatabase database;
    DatabaseReference coffeeList;

    List<Coffee> coffees;
    FirebaseRecyclerAdapter<Coffee, CoffeeViewHolder> coffeeAdapter;
    FirebaseRecyclerAdapter<Coffee, CoffeeViewHolder> searchcoffeeAdapter;
    Integer supplierID = 1;
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_coffee, container, false);

        //Firebase
        database = FirebaseDatabase.getInstance();
        coffeeList = database.getReference("coffee/List");

        recyclerCoffee = (RecyclerView)root.findViewById(R.id.recycler_coffee);
        recyclerCoffee.setHasFixedSize(true);
        recyclerCoffee.setLayoutManager(new LinearLayoutManager(getContext()));

        loadcoffeeList();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        coffeeAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(searchcoffeeAdapter != null){
            searchcoffeeAdapter.stopListening();
        }
    }

    private void loadcoffeeList() {
        FirebaseRecyclerOptions<Coffee> options;
        Bundle bundle = this.getArguments();
        //System.out.println("supID: " + bundle.getInt(Common.CHOICE_STALL));
        if(bundle != null) {
             supplierID = bundle.getInt(Common.CHOICE_STALL, 1);
        }
        loadCoffeeBySupId(supplierID);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
       if(supplierID == 0){
           getActivity().getMenuInflater().inflate(R.menu.coffee_search, menu);
           MenuItem searchItem = menu.findItem(R.id.action_search);
           SearchView searchView = (SearchView)searchItem.getActionView();
           searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
           searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
               @Override
               public boolean onQueryTextSubmit(String s) {
                   return false;
               }

               @Override
               public boolean onQueryTextChange(String s) {
                   if(s.isEmpty()) recyclerCoffee.setAdapter(coffeeAdapter);
                   else {
                       showSearchcoffeeList(s);
                   }
                   return false;
               }
           });
       }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showSearchcoffeeList(String s) {
        ApiService apiService = Network.getInstance().create(ApiService.class);
        apiService.searchByName(s).enqueue(new Callback<List<Coffee>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
               if(response.body() != null) {
                   coffees = response.body();
                   CoffeeAdapter coffeeAdapter1 = new CoffeeAdapter(coffees, getContext());
                   coffeeAdapter1.notifyDataSetChanged();
                   recyclerCoffee.setAdapter(coffeeAdapter1);
               }
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
                Toast.makeText(getContext(), "Call Error!", Toast.LENGTH_SHORT).show();
            }
        });
//        searchcoffeeAdapter.startListening();
//        searchcoffeeAdapter.notifyDataSetChanged();
//        recyclerCoffee.setAdapter(searchcoffeeAdapter);
    }

      private void loadCoffeeBySupId(Integer supId){
        Call<List<Coffee>> call = Network.getInstance().create(ApiService.class).getCoffeesBySupplier(supId);
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                if (response.isSuccessful()) {
                    coffees = response.body();
                    System.out.println(response.body());
                    System.out.println(coffees);
                    CoffeeAdapter coffeeAdapter = new CoffeeAdapter(coffees, getContext());
                    coffeeAdapter.notifyDataSetChanged();
                    recyclerCoffee.setAdapter(coffeeAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                System.out.println("wrong network");           }
        });
    }
}
