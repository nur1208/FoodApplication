package com.example.foodapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapplication.Domain.FoodDomain;
import com.example.foodapplication.Helpar.ManagementCart;
import com.example.foodapplication.R;

public class ShowDetailsActivity extends AppCompatActivity {
    private TextView addToCartTxt, titleTxt, feeTxt, descriptionTxt, numberOrderTxt, totalPriceTxt, caloriesTxt, timeTxt, starTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        managementCart = new ManagementCart(this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableRecurseID = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableRecurseID).into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        caloriesTxt.setText(object.getCalories() + " Cal");
        starTxt.setText(object.getStart() + " Star");
        timeTxt.setText(object.getTime() + " Min");
        totalPriceTxt.setText("$" + (numberOrder * object.getFee()));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText("$" + (numberOrder * object.getFee()));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }

                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText("$" + (numberOrder * object.getFee()));
            }
        });

        addToCartTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);

                startActivity(new Intent(ShowDetailsActivity.this, CartActivity.class));
            }
        });
    }

    private void initView() {
        addToCartTxt = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleShowTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCartShowBtn);
        minusBtn = findViewById(R.id.minusCartShowBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        caloriesTxt = findViewById(R.id.caloriesTxt);
        timeTxt = findViewById(R.id.timeTxt);
        starTxt = findViewById(R.id.starTxt);
    }
}