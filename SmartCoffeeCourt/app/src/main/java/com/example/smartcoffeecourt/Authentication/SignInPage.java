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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.ApiService.AuthenticationResponse;
import com.example.smartcoffeecourt.ApiService.UserLoginRequest;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.HomePage;
import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPage extends AppCompatActivity {

    TextView textSignUp, textForgotPassword;
    Button btnSignIn;
    EditText editPassword, editEmail;
    CheckBox checkBoxRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        checkBoxRemember = (CheckBox)findViewById(R.id.checkBoxRemember);
        textSignUp = (TextView)findViewById(R.id.text_sign_up);
        textForgotPassword = (TextView)findViewById(R.id.textForgotPassword);
        editPassword = (EditText)findViewById(R.id.editTextPassword);
        editEmail = (EditText)findViewById(R.id.editTextEmail);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpPage.class);
                startActivity(intent);
            }
        });

        textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInPage.this, ForgotPasswordPage.class);
                startActivity(intent);
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });
        Paper.init(this);
        checkSavedUser();
    }

    private void checkInput(){
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(SignInPage.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignInPage.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            signIn(email, password);
        }
    }

    private void signIn(final String email, final String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignInPage.this);
        mDialog.setMessage("Xin vui lòng đợi...");
        mDialog.show();

        ApiService apiService = Network.getInstance().create(ApiService.class);
        UserLoginRequest ulr = new UserLoginRequest();
        ulr.setEmail(email);
        ulr.setPassword(password);
        apiService.signIn(ulr).enqueue(new Callback<AuthenticationResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.isSuccessful()) {
                    Common.user = response.body().getUser();
                    Common.userId = response.body().getUser().getId().toString();
                    mDialog.dismiss();
                    if(checkBoxRemember.isChecked()){
                        Paper.book().write(Common.TOKEN,response.body().getToken());
                        Paper.book().write(Common.USER_UID,Common.userId);
                        Paper.book().write(Common.EMAIL_KEY,email);
                        Paper.book().write(Common.PASSWORD_KEY,password);
                    }

                    Intent homePageIntent = new Intent(SignInPage.this, HomePage.class);
                    startActivity(homePageIntent);
                    finish();
                } else {
                    mDialog.dismiss();
                    Toast.makeText(SignInPage.this, "Authentication bị lỗi. Tài khoản hoặc mật khẩu không đúng!!!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(SignInPage.this, "Authentication bị lỗi. Có gì đó không đúng!!!", Toast.LENGTH_SHORT).show();

            }
        });


    }



    private void checkSavedUser() {
        String email = Paper.book().read(Common.EMAIL_KEY);
        String password = Paper.book().read(Common.PASSWORD_KEY);
        String token = Paper.book().read(Common.TOKEN);
        if(token != null && email != null && password != null){
           signIn(email,password);
        }
    }
}

