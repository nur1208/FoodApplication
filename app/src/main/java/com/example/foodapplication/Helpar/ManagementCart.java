package com.example.foodapplication.Helpar;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapplication.Domain.FoodDomain;
import com.example.foodapplication.Interface.ChangeCartListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }


    public void insertFood(FoodDomain newItem) {
        ArrayList<FoodDomain> listFoods = getListCart();
        boolean exitAlready = false;
        int n = 0;
        for (int i = 0; i < listFoods.size(); i++) {
            if (listFoods.get(i).getTitle().equals(newItem.getTitle())) {
                exitAlready = true;
                n = i;
                break;
            }
        }

        if (exitAlready) {
            listFoods.get(n).setNumberInCart(newItem.getNumberInCart() + listFoods.get(n).getNumberInCart());
        } else {
            listFoods.add(newItem);
        }


        tinyDB.putListObject("CardList", listFoods);

        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }


    public void removeFood(ArrayList<FoodDomain> listFood, int position, ChangeCartListener changeCartListener) {

        listFood.remove(position);

        tinyDB.putListObject("CardList", listFood);

        Toast.makeText(context, "Item Removed from Cart", Toast.LENGTH_SHORT).show();

        changeCartListener.changed();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    public void minusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeCartListener changeCartListener) {
        if (listFood.get(position).getNumberInCart() == 1) {
            listFood.remove(position);
        } else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() - 1);
        }

        tinyDB.putListObject("CardList", listFood);
        changeCartListener.changed();
    }


    public void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeCartListener changeCartListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listFood);

        changeCartListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<FoodDomain> listFood2 = getListCart();
        double fee = 0;
        for (int i = 0; i < listFood2.size(); i++) {
            fee = fee + (listFood2.get(i).getFee() * listFood2.get(i).getNumberInCart());
        }

        return fee;
    }
}
