package com.example.managerapp.SupplierSide.Login;

public interface LoginContract {
    interface View{
        void showToast(String message);
        void startHomePage();
        void showWaitingDialog();
        void closeWaitingDialog();

        void showAccountErrorDialog(String error, String message);
    }
    interface Presenter{
        void login(String phone, String password);


    }
}
