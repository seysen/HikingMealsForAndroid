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
import com.seysen.hikingmealsforandroid.core.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private LayoutInflater inflater;
    private ArrayList<Product> products;
    private static ClickListener clickListener;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.products_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.nameView.setText(product.getProductName());
        holder.energyView.setText(String.format("%.1f", + product.getEnergy()).replace(",", "."));
        holder.proteinView.setText(String.format("%.1f", + product.getProtein()).replace(",", "."));
        holder.fatView.setText(String.format("%.1f", + product.getFat()).replace(",", "."));
        holder.carbohydrateView.setText(String.format("%.1f", + product.getCarbohydrate()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public ProductAdapter(Context context, ArrayList<Product> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        products = items;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView nameView;
        private final TextView energyView;
        private final TextView proteinView;
        private final TextView fatView;
        private final TextView carbohydrateView;

        public ProductViewHolder(@NonNull View view) {
            super(view);
            nameView = view.findViewById(R.id.productName);
            energyView = view.findViewById(R.id.product_energy);
            proteinView = view.findViewById(R.id.product_protein);
            fatView = view.findViewById(R.id.product_fat);
            carbohydrateView = view.findViewById(R.id.product_carbohydrate);
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

    public void setOnItemClickListener(ClickListener clickListener) {
        ProductAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

}
