package com.example.smartcoffeecourt;

import com.example.smartcoffeecourt.Model.User;
import com.example.smartcoffeecourt.R;

import java.text.NumberFormat;
import java.util.Locale;

public class Common {
    public static User user;
    public static String userId;
    public static final String INTENT_coffee_REF = "coffeeRef";
    public static final String EMAIL_KEY = "Email";
    public static final String TOKEN = "Token";
    public static final String USER_UID = "UID";
    public static final String PASSWORD_KEY = "Password";
    public static final String CHOICE_STALL = "supplierID";
    public static String convertCodeToStatus(String status) {
        // 0: preparing, 1: ready, 2: received
        if (status.equals("0")) return "Đang chuẩn bị";
        else return "Đã hoàn thành";
    }

    public static String convertPriceToVND(String price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return fmt.format(Integer.parseInt(price));
    }

    public static String convertPriceToVND(Float price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return fmt.format(price);
    }

    public static int convertDiscountToImage(String discount){
        if(Integer.parseInt(discount) > 10) return R.drawable.bigdiscount;
        else if(Integer.parseInt(discount) > 0) return R.drawable.smalldiscount;
        return 0;
    }

    public static int convertOutOfOrderToImage(){
        return R.drawable.outoforder;
    }

    public static String convertCodeToType(String type) {
        // 0: Eat it, 1: Take away
        if (type.equals("0")) return "Ăn tại chỗ";
        else return "Mang đi";
    }


}
