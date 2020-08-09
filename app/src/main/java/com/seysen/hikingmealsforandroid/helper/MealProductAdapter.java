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
import com.seysen.hikingmealsforandroid.core.MealProduct;

import java.util.ArrayList;

public class MealProductAdapter extends RecyclerView.Adapter<MealProductAdapter.MealProductViewHolder> {
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private LayoutInflater inflater;
    private ArrayList<MealProduct> products;
    private static ClickListener clickListener;

    @NonNull
    @Override
    public MealProductAdapter.MealProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_products_item, viewGroup, false);
        return new MealProductAdapter.MealProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealProductAdapter.MealProductViewHolder holder, int position) {
        MealProduct product = products.get(position);
        holder.mealProductNameView.setText(product.getProductName());
        holder.mealProductEnergyView.setText(String.format("%.1f", + product.getEnergy()).replace(",", "."));
        holder.mealProductProteinView.setText(String.format("%.1f", + product.getProtein()).replace(",", "."));
        holder.mealProductFatView.setText(String.format("%.1f", + product.getFat()).replace(",", "."));
        holder.mealProductCarbohydrateView.setText(String.format("%.1f", + product.getCarbohydrate()).replace(",", "."));
        holder.mealProductWeightView.setText(String.format("%.1f", + product.getWeight()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public MealProductAdapter(Context context, ArrayList<MealProduct> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        products = items;
    }

    public static class MealProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView mealProductNameView;
        private final TextView mealProductEnergyView;
        private final TextView mealProductProteinView;
        private final TextView mealProductFatView;
        private final TextView mealProductCarbohydrateView;
        private final TextView mealProductWeightView;

        public MealProductViewHolder(@NonNull View view) {
            super(view);
            mealProductNameView = view.findViewById(R.id.meal_product_item_name);
            mealProductEnergyView = view.findViewById(R.id.meal_product_energy);
            mealProductProteinView = view.findViewById(R.id.meal_product_protein);
            mealProductFatView = view.findViewById(R.id.meal_product_fat);
            mealProductCarbohydrateView = view.findViewById(R.id.meal_product_carbohydrate);
            mealProductWeightView = view.findViewById(R.id.meal_product_item_weight);
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

    public void setOnItemClickListener(MealProductAdapter.ClickListener clickListener) {
        MealProductAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }
}