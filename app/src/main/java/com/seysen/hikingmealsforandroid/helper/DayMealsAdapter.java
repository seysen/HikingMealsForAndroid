package com.seysen.hikingmealsforandroid.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Meal;

import java.util.ArrayList;

public class DayMealsAdapter extends RecyclerView.Adapter<DayMealsAdapter.DayMealViewHolder> {

    private static final String TAG = "Meal_Adapter";
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private LayoutInflater inflater;
    private static ArrayList<Meal> mMeals;
    private static DayMealsAdapter.ClickListener clickListener;

    @NonNull
    @Override
    public DayMealsAdapter.DayMealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_item, viewGroup, false);
        return new DayMealsAdapter.DayMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayMealsAdapter.DayMealViewHolder holder, int position) {
        Log.d(TAG,"Meal Adapter onBindViewHolder");
        Meal meal = mMeals.get(position);
        Log.d(TAG,"Meal = " + meal + " weight = " + meal.getWeight());
        Log.d(TAG,"Meal Energy = " + meal.getEnergy());
        holder.nameView.setText(meal.getMealName());
        holder.energyView.setText(String.format("%.1f", + meal.getEnergy()).replace(",", "."));
        holder.proteinView.setText(String.format("%.1f", + meal.getProtein()).replace(",", "."));
        holder.fatView.setText(String.format("%.1f", + meal.getFat()).replace(",", "."));
        holder.carbohydrateView.setText(String.format("%.1f", + meal.getCarbohydrate()).replace(",", "."));
        holder.weightView.setText(String.format("%.1f", + meal.getWeight()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return mMeals.size();
    }

    public DayMealsAdapter(Context context, ArrayList<Meal> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mMeals = items;
    }

    public static class DayMealViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private final TextView nameView;
        private final TextView energyView;
        private final TextView proteinView;
        private final TextView fatView;
        private final TextView carbohydrateView;
        private final TextView weightView;

        public DayMealViewHolder(@NonNull View view) {
            super(view);
            nameView = view.findViewById(R.id.mealName);
            energyView = view.findViewById(R.id.meal_energy);
            proteinView = view.findViewById(R.id.meal_protein);
            fatView = view.findViewById(R.id.meal_fat);
            carbohydrateView = view.findViewById(R.id.meal_carbohydrate);
            weightView = view.findViewById(R.id.meal_weight);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(DayMealsAdapter.ClickListener clickListener) {
        DayMealsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemLongClick(int position, View v);
    }
}
