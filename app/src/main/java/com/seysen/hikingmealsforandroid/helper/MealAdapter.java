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
        Meal meal = meals.get(position);
        holder.nameView.setText(meal.getMealName());
        holder.energyView.setText(String.format("%.1f", + meal.getEnergy()).replace(",", "."));
        holder.proteinView.setText(String.format("%.1f", + meal.getProtein()).replace(",", "."));
        holder.fatView.setText(String.format("%.1f", + meal.getFat()).replace(",", "."));
        holder.carbohydrateView.setText(String.format("%.1f", + meal.getCarbohydrate()).replace(",", "."));
        holder.weightView.setText(String.format("%.1f", + meal.getWeight()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public MealAdapter(Context context, ArrayList<Meal> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        meals = items;
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView nameView;
        private final TextView energyView;
        private final TextView proteinView;
        private final TextView fatView;
        private final TextView carbohydrateView;
        private final TextView weightView;

        public MealViewHolder(@NonNull View view) {
            super(view);
            nameView = view.findViewById(R.id.mealName);
            energyView = view.findViewById(R.id.meal_energy);
            proteinView = view.findViewById(R.id.meal_protein);
            fatView = view.findViewById(R.id.meal_fat);
            carbohydrateView = view.findViewById(R.id.meal_carbohydrate);
            weightView = view.findViewById(R.id.meal_weight);
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
