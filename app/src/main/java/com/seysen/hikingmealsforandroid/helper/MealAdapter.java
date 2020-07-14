package com.seysen.hikingmealsforandroid.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Meal;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private LayoutInflater inflater;
    private ArrayList<Meal> meals;
    private static MealAdapter.ClickListener clickListener;

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_item, viewGroup, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal product = meals.get(position);
        holder.nameView.setText(product.getMealName());
        holder.energyView.setText(String.format("%.1f", + product.getEnergy()).replace(",", "."));
        holder.proteinView.setText(String.format("%.1f", + product.getProtein()).replace(",", "."));
        holder.fatView.setText(String.format("%.1f", + product.getFat()).replace(",", "."));
        holder.carbohydrateView.setText(String.format("%.1f", + product.getCarbohydrate()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView nameView;
        private final TextView energyView;
        private final TextView proteinView;
        private final TextView fatView;
        private final TextView carbohydrateView;

        public MealViewHolder(@NonNull View view) {
            super(view);
            nameView = (TextView)view.findViewById(R.id.mealName);
            energyView = (TextView)view.findViewById(R.id.meal_energy);
            proteinView = (TextView)view.findViewById(R.id.meal_protein);
            fatView = (TextView)view.findViewById(R.id.meal_fat);
            carbohydrateView = (TextView)view.findViewById(R.id.meal_carbohydrate);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(MealAdapter.ClickListener clickListener) {
        MealAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
