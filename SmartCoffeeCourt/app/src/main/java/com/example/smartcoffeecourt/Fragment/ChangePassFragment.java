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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Adapter.OrderAdapter;
import com.example.smartcoffeecourt.ApiService.ApiService;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Model.Order;
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
                if(!newPassConfirm.equals(newPass)){

                    Toast.makeText(getActivity(), "Mật khẩu không khớp!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(checkOldPass()){
                    Toast.makeText(getActivity(), "Mật khẩu cũ không đúng!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        return root;
    }

    private void changePass(Integer userId) {
        // code của tình
    }

    private boolean checkOldPass(){
        // code của tình
        return oldPass.getText().toString().equals("mat khau cũ trong db");
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
