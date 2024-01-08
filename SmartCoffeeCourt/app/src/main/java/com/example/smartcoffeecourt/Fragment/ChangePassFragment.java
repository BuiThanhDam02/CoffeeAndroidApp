package com.example.smartcoffeecourt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.OrderAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.ApiService.ChangePasswordRequest;
import com.example.smartcoffeecourt.Authentication.ForgotPasswordPage;
import com.example.smartcoffeecourt.Authentication.SignInPage;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.HomePage;
import com.example.smartcoffeecourt.Model.Order;
import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassFragment  extends Fragment {

    Button btnChangePass;
    EditText newPass,oldPass,newPassConfirm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_pass, container, false);

        newPass = root.findViewById(R.id.editTextNewPass);
        oldPass = root.findViewById(R.id.editTextOldPass);
        newPassConfirm = root.findViewById(R.id.editTextNewPassConfirm);

        btnChangePass = root.findViewById(R.id.btnChangePass);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newPassConfirm.getText().toString().equals(newPass.getText().toString())){
                    Toast.makeText(getActivity(), "Mật khẩu không khớp!", Toast.LENGTH_LONG).show();
                } else {
                    changePass(new ChangePasswordRequest(Long.parseLong(Common.userId),oldPass.getText().toString(), newPass.getText().toString()));
                }

            }
        });

        return root;
    }

    private void changePass(ChangePasswordRequest changePasswordRequest) {
        Call<User> call = Network.getInstance().create(ApiService.class).changePassword(changePasswordRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    if(user != null) {
                        Common.user = user;
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Không đúng mật khẩu cũ!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Network Error!", Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
