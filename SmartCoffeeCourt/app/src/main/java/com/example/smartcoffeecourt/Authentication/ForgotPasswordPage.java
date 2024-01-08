package com.example.smartcoffeecourt.Authentication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.CoffeeDetail.CoffeeDetailPage;
import com.example.smartcoffeecourt.CoffeeDetail.CommentPage;
import com.example.smartcoffeecourt.HomePage;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;
import com.example.smartcoffeecourt.WelcomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.View.VISIBLE;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPage extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    EditText editTextEmail;
    Button btnSendEmail;
    FirebaseDatabase database;
    DatabaseReference table_user;
    FirebaseAuth firebaseAuth;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        toolbar = (Toolbar)findViewById(R.id.toolbarForgotPassword);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        btnSendEmail = (Button)findViewById(R.id.btnSendEmail);

        toolbar.setTitle("Quên mật khẩu???");
        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User/List");
        firebaseAuth = FirebaseAuth.getInstance();

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                forgotPassword();
            }
        });
    }

    private void forgotPassword(){
        String email = editTextEmail.getText().toString();
        if(email.isEmpty()) return;
        progressBar.setVisibility(VISIBLE);

        Call<Void> forgotPassword = Network.getInstance().create(ApiService.class).forgotPassword(email);
        forgotPassword.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgotPasswordPage.this, "Mật khẩu mới đã được gửi về Email!", Toast.LENGTH_LONG).show();
                    Intent btnContinue = new Intent(ForgotPasswordPage.this, SignInPage.class);
                    startActivity(btnContinue);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgotPasswordPage.this, "Email không đúng!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ForgotPasswordPage.this, "Network Error!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
