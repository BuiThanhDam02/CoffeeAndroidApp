package com.example.smartcoffeecourt.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.Network.Network;
import com.example.smartcoffeecourt.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment  extends Fragment {

    User userProfile;
    EditText editTextEmail,editTextPhone,editTextUserName,editTextAddress;

    Button updateUser;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        editTextEmail = root.findViewById(R.id.editTextEmail);
        editTextPhone = root.findViewById(R.id.editTextPhone);
        editTextUserName = root.findViewById(R.id.editTextUserName);
        editTextAddress = root.findViewById(R.id.editTextAddress);

        updateUser = (Button) root.findViewById(R.id.btnUpdate);
        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
                if(!checkEmail(editTextEmail.getText().toString())){
                    System.out.println("email k dung");
                    Toast.makeText(getContext(), "Email không đúng!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!checkPhone(editTextPhone.getText().toString())){
                    System.out.println("sdt k dung");
                    Toast.makeText(getActivity(), "SĐT không đúng!", Toast.LENGTH_LONG).show();
                    return;
                }
                User user = new User();
                user.setEmail(String.valueOf(editTextEmail.getText()));
                user.setPhone(String.valueOf(editTextPhone.getText()));
                user.setAddress(String.valueOf(editTextAddress.getText()));
                user.setUsername(String.valueOf(editTextUserName.getText()));
                updateUser(user);
            }
        });
        return root;
    }

    private void loadUserProfile(Long userId){
        Call<User> call = Network.getInstance().create(ApiService.class).getUserProfile(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userProfile = response.body();
                    editTextEmail.setText(userProfile.getEmail());
                    editTextPhone.setText(userProfile.getPhone());
                    editTextUserName.setText(userProfile.getUsername());
                    editTextAddress.setText(userProfile.getAddress());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("wrong network");           }
        });
    }

    private void updateUser(User user){
        // code của tình :v
    }

    @Override
    public void onStart() {
        loadUserProfile(Long.valueOf(Common.userId));
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

   private boolean checkEmail(String email){
       String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
       Pattern pattern = Pattern.compile(EMAIL_REGEX);
       Matcher matcher = pattern.matcher(email);
       return matcher.matches();
   }

    private boolean checkPhone(String phone){
        String PHONE_NUMBER_REGEX = "^(03|05|07|08)[0-9]{8}$";
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
