package com.seysen.hikingmealsforandroid.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.MealProduct;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private LayoutInflater inflater;
    private ArrayList<MealProduct> products;

    @NonNull
    @Override
    public ShoppingListAdapter.ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shopping_list_item, viewGroup, false);
        return new ShoppingListAdapter.ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ShoppingListViewHolder shoppingListViewHolder, int i) {
        MealProduct product = products.get(i);
        shoppingListViewHolder.productNameView.setText(product.getProductName());
        shoppingListViewHolder.productWeightView.setText(String.format("%.1f", + product.getWeight()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public ShoppingListAdapter(Context context, ArrayList<MealProduct> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        products = items;
    }

    public class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox productNameView;
        private final TextView productWeightView;

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameView = itemView.findViewById(R.id.shopping_list_product);
            productWeightView = itemView.findViewById(R.id.shopping_list_product_weight);
        }
    }
}
