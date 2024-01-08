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
                if(!checkEmail(editTextEmail.getText().toString())){
                    Toast.makeText(getContext(), "Email không đúng định dạng!", Toast.LENGTH_LONG).show();
                    return;
                } else if(!checkPhone(editTextPhone.getText().toString())){
                    Toast.makeText(getActivity(), "SĐT không đúng định dạng!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    User user = new User();
                    user.setId(Integer.parseInt(Common.userId));
                    user.setEmail(String.valueOf(editTextEmail.getText()));
                    user.setPhone(String.valueOf(editTextPhone.getText()));
                    user.setAddress(String.valueOf(editTextAddress.getText()));
                    user.setUsername(String.valueOf(editTextUserName.getText()));
                    updateUser(user);
                }

            }
        });
        return root;
    }

    private void loadUserProfile(){
        userProfile = Common.user;
        editTextEmail.setText(userProfile.getEmail());
        editTextPhone.setText(userProfile.getPhone());
        editTextUserName.setText(userProfile.getUsername());
        editTextAddress.setText(userProfile.getAddress());
    }

    private void updateUser(User user){
        Call<User> call = Network.getInstance().create(ApiService.class).updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User userUpdate = response.body();
                    System.out.println("user update: " + userUpdate);
                    if(userUpdate != null){
                        Common.user = userUpdate;
                        userProfile = Common.user;
                        Toast.makeText(getContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Email đã có người sử dụng!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Network Error!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart() {
        loadUserProfile();
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
        String PHONE_NUMBER_REGEX = "^(03|05|07|08|09)[0-9]{8}$";
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
