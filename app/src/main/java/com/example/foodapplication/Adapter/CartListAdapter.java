package com.example.foodapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapplication.Domain.FoodDomain;
import com.example.foodapplication.Helpar.ManagementCart;
import com.example.foodapplication.Interface.ChangeCartListener;
import com.example.foodapplication.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<FoodDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChangeCartListener changeCartListener;

    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected, Context context, ChangeCartListener changeCartListener) {
        this.listFoodSelected = listFoodSelected;
        managementCart = new ManagementCart(context);
        this.changeCartListener = changeCartListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("$" + listFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("$" + Math.round((listFoodSelected.get(position).getNumberInCart() * listFoodSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));


        int drawableResId = holder.itemView.getContext().getResources().getIdentifier(listFoodSelected.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResId).into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(listFoodSelected, position, new ChangeCartListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeCartListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(listFoodSelected, position, new ChangeCartListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeCartListener.changed();
                    }
                });
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.removeFood(listFoodSelected ,position, new ChangeCartListener(){
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeCartListener.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, totalEachItem, num;
        ImageView pic, plusItem, minusItem;
        ConstraintLayout deleteBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.picItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            plusItem = itemView.findViewById(R.id.plusCartShowBtn);
            minusItem = itemView.findViewById(R.id.minusCartShowBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }

        ;
    }
}
