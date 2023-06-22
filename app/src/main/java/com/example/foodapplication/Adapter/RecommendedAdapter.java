package com.example.foodapplication.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapplication.Activity.ShowDetailsActivity;
import com.example.foodapplication.Domain.FoodDomain;
import com.example.foodapplication.R;

import java.util.AbstractList;
import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    ArrayList<FoodDomain> recommendedDomains;

    public RecommendedAdapter(ArrayList<FoodDomain> recommendedDomains) {
        this.recommendedDomains = recommendedDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommended, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(recommendedDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(recommendedDomains.get(position).getFee()));


        int drawableResId = holder.itemView.getContext().getResources().getIdentifier(recommendedDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResId).into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                intent.putExtra("object", recommendedDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedDomains.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic, addBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
            fee = itemView.findViewById(R.id.fee);
        }

        ;
    }
}
