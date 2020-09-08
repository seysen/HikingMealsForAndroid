package com.seysen.hikingmealsforandroid;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.core.Meal;
import com.seysen.hikingmealsforandroid.core.MealProduct;
import com.seysen.hikingmealsforandroid.core.Product;
import com.seysen.hikingmealsforandroid.fragments.MealsFragment;
import com.seysen.hikingmealsforandroid.helper.CustomDialogFragment;
import com.seysen.hikingmealsforandroid.helper.Datable;
import com.seysen.hikingmealsforandroid.helper.MealDetailDialogFragment;
import com.seysen.hikingmealsforandroid.helper.MealProductAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class MealDetailActivity extends AppCompatActivity implements Datable {
    private static final String TAG = "meal_detail";
    public static final String ID_KEY = "meal_id";
    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    public static final int REQUEST_CREATE_TYPE=1;
    public static final int REQUEST_EDIT_TYPE=2;
    public static final String PRODUCTNAME = "product";
    public static final String MEALNAME = "meal";
    private int position;
    private MealProductAdapter adapter;
    private Meal meal;
    private EditText mealName;
    private TextView mealEnergy;
    private TextView mealProtein;
    private TextView mealFat;
    private TextView mealCarbohydrate;
    private TextView mealWeight;
    private ArrayList<MealProduct> mProducts = new ArrayList<>();
    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        Log.d(TAG,"Meal Detail Created");
        mealName = findViewById(R.id.meal_name_detail);
        mealEnergy = findViewById(R.id.meal_energy_detail);
        mealProtein = findViewById(R.id.meal_protein_detail);
        mealFat = findViewById(R.id.meal_fat_detail);
        mealCarbohydrate = findViewById(R.id.meal_carbohydrate_detail);
        mealWeight = findViewById(R.id.meal_weight_detail);
        RecyclerView mealProducts = findViewById(R.id.meal_products);
        mealProducts.setLayoutManager(new LinearLayoutManager(mealProducts.getContext()));
        adapter = new MealProductAdapter(this,mProducts);
        mealProducts.setAdapter(adapter);
        adapter.setOnItemClickListener(new MealProductAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Log.d(TAG, "View = " + v);
                Intent intent = new Intent(v.getContext(), MealAddProductActivity.class);
                intent.putExtra(ID_KEY, position);
                intent.putExtra(PRODUCTNAME,mProducts.get(position));
                Log.d(TAG, String.valueOf(intent));
                Log.d(TAG, String.valueOf(intent.getIntExtra(ID_KEY,0)));
                Log.d(TAG, String.valueOf(intent.getExtras()));
                startActivityForResult(intent, REQUEST_EDIT_TYPE);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
                String productName = mProducts.get(position).getProductName();
                Log.d(TAG, "productName = " + productName);
                MealDetailDialogFragment dialog = new MealDetailDialogFragment();
                Bundle args = new Bundle();
                args.putString("item","product");
                args.putString("name", productName);
                args.putInt("position", position);

                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(),"custom");
            }
        });

        final FloatingActionButton fab = findViewById(R.id.meal_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent (v.getContext(), MealAddProductActivity.class);
                startActivityForResult(intent,REQUEST_CREATE_TYPE);
            }
        });

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            Log.d(TAG,"Has arguments " + arguments);
            Log.d(TAG,"Get position with KEY = " + ID_KEY);
            position = arguments.getInt(ID_KEY);
            Log.d(TAG,"Position = " + position);
            meal = arguments.getParcelable(MEALNAME);
            Log.d(TAG,String.valueOf(meal));
            assert meal != null;
            ArrayList<MealProduct> products = meal.getMealProducts();
            Log.d(TAG,String.valueOf(products));
            mealName.setText(meal.getMealName());
            mealEnergy.setText(String.format("%.1f", + meal.getEnergy()).replace(",", "."));
            mealProtein.setText(String.format("%.1f", + meal.getProtein()).replace(",", "."));
            mealFat.setText(String.format("%.1f", + meal.getFat()).replace(",", "."));
            mealCarbohydrate.setText(String.format("%.1f", + meal.getCarbohydrate()).replace(",", "."));
            mealWeight.setText(String.format("%.1f", + meal.getWeight()).replace(",", "."));
            for (MealProduct product: products) {
                mProducts.add(product);
                Log.d(TAG,"Add product " + product.getProductName());
            }
            Log.d(TAG,"notifyDataSetChanged");
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"OnActivityResult");
        if (resultCode==RESULT_OK) {
            if (requestCode == REQUEST_EDIT_TYPE) {
                Log.d(TAG, "Request edit");
                Log.d(TAG, "Result OK");
                assert data != null;
                int position = data.getIntExtra(ID_KEY,0);
                MealProduct mProduct = data.getParcelableExtra(PRODUCTNAME);
                /*MealProduct mProduct = data.getParcelableExtra(PRODUCTNAME);
                mProducts.set(position, mProduct);*/
                MealProduct mealProduct = mProducts.get(position);
                assert mProduct != null;
                mealProduct.setProduct(mProduct.getProduct());
                mealProduct.setWeight(mProduct.getWeight());


            } else if (requestCode == REQUEST_CREATE_TYPE) {
                Log.d(TAG, "Result OK");
                assert data != null;
                int position = data.getIntExtra(ID_KEY, 0);
                MealProduct mProduct = data.getParcelableExtra(PRODUCTNAME);
                mProducts.add(mProduct);
            }
            else {
                Log.d(TAG, "Result super");
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
        Log.d(TAG, "Notify data changed");
        updateMeal();
        adapter.notifyDataSetChanged();
    }

    public void onOKClick (View view) {
        Log.d(TAG, "OK click");
        //<TODO> Add check on empty name
        if (meal==null) {
            meal = new Meal(mealName.getText().toString(), mProducts);
        }
        meal.setMealName(mealName.getText().toString());
        meal.setMealProducts(mProducts);
        Intent data = new Intent();
        data.putExtra(ID_KEY,position);
        data.putExtra(MEALNAME,meal);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onCancelClick (View view){
        Log.d(TAG, "Cancel click");
        setResult(RESULT_CANCELED);
        finish();
    }

    public void updateMeal() {
        if (meal==null) {
            meal = new Meal(mealName.getText().toString(), mProducts);
        }
        meal.setMealProducts(mProducts);
        mealEnergy.setText(String.format("%.1f", + meal.getEnergy()).replace(",", "."));
        mealProtein.setText(String.format("%.1f", + meal.getProtein()).replace(",", "."));
        mealFat.setText(String.format("%.1f", + meal.getFat()).replace(",", "."));
        mealCarbohydrate.setText(String.format("%.1f", + meal.getCarbohydrate()).replace(",", "."));
        mealWeight.setText(String.format("%.1f", + meal.getWeight()).replace(",", "."));
    }

    @Override
    public void removeItem(int position) {
        Log.d(TAG, "removeItem");
        mProducts.remove(position);
        adapter.notifyDataSetChanged();
        updateMeal();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
