package com.example.smartcoffeecourt.Authentication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcoffeecourt.ApiService.ApiService;

import com.example.smartcoffeecourt.ApiService.UserRegistrationRequest;
import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPage extends AppCompatActivity {

    EditText editUserName, editPassword, editEmail, editPhone;
    TextView textSignIn;
    Button btnSignUp;

//    FirebaseAuth mAuth;
//    DatabaseReference userReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        mAuth = FirebaseAuth.getInstance();
//        userReference = FirebaseDatabase.getInstance().getReference("User/List");
        editUserName = (EditText)findViewById(R.id.editTextUserName);
        editPhone = (EditText)findViewById(R.id.editTextPhone);
        editEmail = (EditText)findViewById(R.id.editTextEmail);
        editPassword = (EditText)findViewById(R.id.editTextPassword);

        textSignIn = findViewById(R.id.text_sign_in);
        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpPage.this, SignInPage.class));
            }
        });
        btnSignUp = findViewById(R.id.btnSignUp1);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private boolean checkInput(String email, String username, String password, String phone) {

        if(TextUtils.isEmpty(username)){
            Toast.makeText(SignUpPage.this, "Vui lòng nhập tên người dùng", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(SignUpPage.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(SignUpPage.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(SignUpPage.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.length() < 6){
            Toast.makeText(SignUpPage.this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(phone.length() < 10){
            Toast.makeText(SignUpPage.this,"Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void signUp() {
        final String email = editEmail.getText().toString();
        final String username = editUserName.getText().toString();
        String password = editPassword.getText().toString();
        final String phone = editPhone.getText().toString();
        if(checkInput(email, username, password, phone)){
            final ProgressDialog mDialog = new ProgressDialog(SignUpPage.this);
            mDialog.setMessage("Xin vui lòng đợi...");
            mDialog.show();
            ApiService apiService = Network.getInstance().create(ApiService.class);
            UserRegistrationRequest u  = new UserRegistrationRequest();
            u.setEmail(email);
            u.setUsername(username);
            u.setPassword(password);
            u.setPhone(phone);
            apiService.signUp(u).enqueue(new Callback<User>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(Call<User> call, Response<User> response) {


                        mDialog.dismiss();
                        Toast.makeText(SignUpPage.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                        finish();


                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    mDialog.dismiss();
                    Toast.makeText(SignUpPage.this, "Email đã tồn tại hoặc không hợp lệ. Đăng ký không thành công.", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}