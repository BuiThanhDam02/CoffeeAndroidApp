package com.example.smartcoffeecourt;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcoffeecourt.Authentication.SignInPage;
import com.example.smartcoffeecourt.Database.Database;

public class WelcomePage extends AppCompatActivity {

    Button btnContinue;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Database(getBaseContext()).cleanCart();
        txtSlogan = (TextView)findViewById(R.id.txtSlogan);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);

        btnContinue = (Button)findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnContinue = new Intent(WelcomePage.this, SignInPage.class);
                startActivity(btnContinue);
            }
        });
    }
}