package com.seysen.hikingmealsforandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.seysen.hikingmealsforandroid.core.Hike;
import com.seysen.hikingmealsforandroid.core.MealProduct;
import com.seysen.hikingmealsforandroid.core.Product;
import com.seysen.hikingmealsforandroid.helper.ShoppingListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingListActivity extends AppCompatActivity {

    private static final String HIKENAME = "hike";
    private static final String TAG = "shopping";

    private ShoppingListAdapter adapter;
    private Hike hike;
    private RecyclerView shoppingList;
    //private ArrayList<MealProduct> shoppingChart = new ArrayList<>();
    ArrayList<MealProduct> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        shoppingList = findViewById(R.id.shopping_list);
        shoppingList.setLayoutManager(new LinearLayoutManager(shoppingList.getContext()));
        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            Log.d(TAG,"Has Args" + arguments);
            hike = arguments.getParcelable(HIKENAME);
            assert hike != null;
            hike.generateShoppingList();
            products = hike.getShoppingList();
            /*for (Map.Entry<String, Double> entry : shippingChart.entrySet()) {
                String productName = entry.getKey();
                Double weight = entry.getValue();
                products.add(new MealProduct(Product.getProduct(productName), weight));
            }*/
        }
        adapter = new ShoppingListAdapter(this,products);
        shoppingList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onOKClick (View view) {
        Intent data = new Intent();
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}