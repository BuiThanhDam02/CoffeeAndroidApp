package com.example.smartcoffeecourt.CoffeeDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcoffeecourt.Cart;
import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Model.Coffee;
import com.example.smartcoffeecourt.Model.Rating;
import com.example.smartcoffeecourt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CoffeeDetailPage extends AppCompatActivity implements RatingDialogListener, CoffeeDetailContract.View{

    TextView txtName, txtPrice, txtDes, txtDiscount, txtQuantity;
    ImageView imgcoffee, imgAddCart, btnUp, btnDown, imgCart, imgOutOfOrder;
    Button btnBackDetail;
    FloatingActionButton btnStar, btnComment,btnLike;
    RatingBar ratingBar;

    CoffeeDetailContract.Presenter presenter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_detail);

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDes = findViewById(R.id.txtDes);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtQuantity = findViewById(R.id.txtQuantity);
        imgcoffee = findViewById(R.id.imgCoffee);
        imgOutOfOrder = findViewById(R.id.outOfOrder_image);
        btnBackDetail = findViewById(R.id.btnBack);
        imgAddCart = findViewById(R.id.imgAddCart);
        imgCart = findViewById(R.id.imgCart);
        btnDown = findViewById(R.id.imgDown);
        btnUp = findViewById(R.id.imgUp);
        btnStar = findViewById(R.id.btnStar);
        btnLike = findViewById(R.id.btnLike);
        btnComment = findViewById(R.id.btnComment);
        ratingBar = findViewById(R.id.ratingBar);

        if(getIntent() != null) {
            String coffeeRef = getIntent().getStringExtra(Common.INTENT_coffee_REF);
            assert coffeeRef != null;
            if (!coffeeRef.isEmpty()) {
                presenter = new CoffeeDetailPresenter(this, coffeeRef);
                presenter.checkLikeCoffee();
                presenter.loadCoffee();
            }
        }
        if (presenter.getIsLikeCoffee()==true){
            btnLike.setImageDrawable(getResources().getDrawable(R.drawable.baseline_favorite_red_24));

        }else{
            btnLike.setImageDrawable(getResources().getDrawable(R.drawable.baseline_favorite_24));

        }


        btnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(CoffeeDetailPage.this, Cart.class);
                startActivity(cartIntent);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = (Integer.parseInt(txtQuantity.getText().toString()) + 1);
                txtQuantity.setText(String.valueOf(a));
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = (Integer.parseInt(txtQuantity.getText().toString()) - 1);
                if(a > 0){
                    txtQuantity.setText(String.valueOf(a));
                }
            }
        });

        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.requestRatingCoffee();
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.getCoffeeComment();
            }
        });
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.likeCoffee();
            }
        });
        imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addCoffeeToCart(txtQuantity.getText().toString());
            }
        });
    }

    @Override
    public void onNegativeButtonClicked() {
    }

    @Override
    public void onPositiveButtonClicked(int valueRating, @NotNull String comments) {
        Rating rating = new Rating(String.valueOf(valueRating), comments);
        presenter.saveRating(rating);
    }


    @Override
    public void showCoffeeDetail(Coffee coffee) {
        Picasso.with(getBaseContext()).load(coffee.getImage()).into(imgcoffee);
        txtPrice.setText(Common.convertPriceToVND(coffee.getPrice()));
        txtName.setText(coffee.getName());
        txtDes.setText(coffee.getDescription());
        txtDiscount.setText(String.format("%s%%", coffee.getDiscount()));
        ratingBar.setRating(Float.parseFloat(coffee.getStar()));
        if(coffee.getStatus().equals("1")){
            imgOutOfOrder.setImageResource(Common.convertOutOfOrderToImage());
        }
    }

    @Override
    public void showRatingDialog(ArrayList<String> levelList) {
        new AppRatingDialog.Builder().setPositiveButtonText("Bình luận").setNegativeButtonText("Hủy")
                .setNoteDescriptions(levelList)
                .setDefaultRating(1).setTitle("Đánh giá món ăn").setDescription("Vui lòng đánh giá món ăn và bình luận những phản hồi của bạn")
                .setTitleTextColor(R.color.colorPrimary).setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Vui lòng viết bình luận của bạn ở đây...").setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(android.R.color.white)
                .setCommentBackgroundColor(R.color.colorPrimaryDark).setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(CoffeeDetailPage.this).show();
    }

    @Override
    public void showCommentPage(String coffeeRef) {
        Intent commentIntent = new Intent(CoffeeDetailPage.this, CommentPage.class);
        commentIntent.putExtra(Common.INTENT_coffee_REF, coffeeRef);
        startActivity(commentIntent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeView() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
