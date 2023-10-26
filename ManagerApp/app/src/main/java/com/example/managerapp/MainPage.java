package com.example.managerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainPage extends AppCompatActivity {

    Button btnStall, btnCourt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStall = findViewById(R.id.btnStall);


        btnStall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSupplierLoginPage();
            }
        });


    }
    private void showSupplierLoginPage(){
        startActivity(new Intent(MainPage.this, com.example.managerapp.SupplierSide.Login.LoginPage.class));
    }

}