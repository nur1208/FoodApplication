package com.example.foodapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapplication.Adapter.CategoryAdapter;
import com.example.foodapplication.Adapter.RecommendedAdapter;
import com.example.foodapplication.Domain.CategoryDomain;
import com.example.foodapplication.Domain.FoodDomain;
import com.example.foodapplication.R;

import java.util.AbstractList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCateogryList, recyclerViewPopularList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCateogry();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class) );
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperioni Pizza", "pizza1", "slices pepperioni, mazzaralle, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));
        foodList.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato", 15.20, 4, 18, 1500));
        foodList.add(new FoodDomain("Vagetalbe Pizza", "pizza3", "olive oil, vegatable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 11.0, 3, 16, 800));

        foodList.add(new FoodDomain("Pepperioni Pizza", "pizza1", "slices pepperioni, mazzaralle, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));
        foodList.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato", 15.20, 4, 18, 1500));
        foodList.add(new FoodDomain("Vagetalbe Pizza", "pizza3", "olive oil, vegatable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 11.0, 3, 16, 800));

        foodList.add(new FoodDomain("Pepperioni Pizza", "pizza1", "slices pepperioni, mazzaralle, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));
        foodList.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato", 15.20, 4, 18, 1500));
        foodList.add(new FoodDomain("Vagetalbe Pizza", "pizza3", "olive oil, vegatable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 11.0, 3, 16, 800));

        foodList.add(new FoodDomain("Pepperioni Pizza", "pizza1", "slices pepperioni, mazzaralle, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));
        foodList.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato", 15.20, 4, 18, 1500));
        foodList.add(new FoodDomain("Vagetalbe Pizza", "pizza3", "olive oil, vegatable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 11.0, 3, 16, 800));
        adapter2 = new RecommendedAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCateogry() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCateogryList = findViewById(R.id.view1);
        recyclerViewCateogryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "cat_1"));
        categoryList.add(new CategoryDomain("Burger", "cat_2"));
        categoryList.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryList.add(new CategoryDomain("Drink", "cat_4"));
        categoryList.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCateogryList.setAdapter(adapter);
    }
}