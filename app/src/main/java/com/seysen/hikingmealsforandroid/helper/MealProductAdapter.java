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
    private static MealProductAdapter.ClickListener clickListener;

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
        holder.nameView.setText(product.getProductName());
        /*holder.energyView.setText(String.format("%.1f", +product.getEnergy()).replace(",", "."));
        holder.proteinView.setText(String.format("%.1f", +product.getProtein()).replace(",", "."));
        holder.fatView.setText(String.format("%.1f", +product.getFat()).replace(",", "."));
        holder.carbohydrateView.setText(String.format("%.1f", +product.getCarbohydrate()).replace(",", "."));*/
        holder.weightView.setText(String.format("%.1f", + product.getWeight()).replace(",", "."));
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

        private final TextView nameView;
        /*private final TextView energyView;
        private final TextView proteinView;
        private final TextView fatView;
        private final TextView carbohydrateView;*/
        private final TextView weightView;

        public MealProductViewHolder(@NonNull View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.meal_product_item_name);
            /*energyView = (TextView) view.findViewById(R.id.product_energy);
            proteinView = (TextView) view.findViewById(R.id.product_protein);
            fatView = (TextView) view.findViewById(R.id.product_fat);
            carbohydrateView = (TextView) view.findViewById(R.id.product_carbohydrate);*/
            weightView = (TextView) view.findViewById(R.id.meal_product_item_weight);
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