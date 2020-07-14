package com.seysen.hikingmealsforandroid;

import android.annotation.SuppressLint;
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
import com.seysen.hikingmealsforandroid.fragments.MealsFragment;
import com.seysen.hikingmealsforandroid.helper.MealProductAdapter;

import java.util.ArrayList;

public class MealDetailActivity extends AppCompatActivity {

    private static final String TAG = "meals_detail";
    public static final int REQUEST_CREATE_TYPE=1;
    public static final int REQUEST_EDIT_TYPE=2;
    private int position;
    private MealProductAdapter adapter;
    private EditText mealName;
    private TextView mealEnergy;
    private TextView mealProtein;
    private TextView mealFat;
    private TextView mealCarbohydrate;
    private RecyclerView mealProducts;
    private static ArrayList<MealProduct> mProducts = new ArrayList<MealProduct>();


    @SuppressLint({"WrongViewCast", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        mealName = findViewById(R.id.meal_name_txt);
        mealEnergy = findViewById(R.id.meal_energy);
        mealProtein = findViewById(R.id.meal_protein);
        mealFat = findViewById(R.id.meal_fat);
        mealCarbohydrate = findViewById(R.id.meal_carbohydrate);
        mealProducts = findViewById(R.id.meal_products);
        mealProducts.setLayoutManager(new LinearLayoutManager(mealProducts.getContext()));
        adapter = new MealProductAdapter(this,mProducts);
        mealProducts.setAdapter(adapter);

        final FloatingActionButton fab = findViewById(R.id.meal_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent (v.getContext(), MealAddProductActivity.class);
                startActivityForResult(intent,REQUEST_CREATE_TYPE);
            }
        });


        Log.d(TAG, "Product detail created");

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            position = arguments.getInt(MealsFragment.ID_KEY);
            Meal meal = arguments.getParcelable("MEAL");
            ArrayList<MealProduct> products = arguments.getParcelableArrayList("PRODUCTS");


            mealName.setText(meal.getMealName());
            mealEnergy.setText(String.format("%.1f", + meal.getEnergy()).replace(",", "."));
            mealProtein.setText(String.format("%.1f", + meal.getProtein()).replace(",", "."));
            mealFat.setText(String.format("%.1f", + meal.getFat()).replace(",", "."));
            mealCarbohydrate.setText(String.format("%.1f", + meal.getCarbohydrate()).replace(",", "."));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void onOKClick (View view) {
        Log.d(TAG, "OK click");
        String mProductName = mealName.getText().toString();
        double mProductEnergy = Double.parseDouble(mealEnergy.getText().toString());
        double mProductProtein = Double.parseDouble(mealProtein.getText().toString());
        double mProductFat = Double.parseDouble(mealFat.getText().toString());
        double mProductCarbohydrate = Double.parseDouble(mealCarbohydrate.getText().toString());
        Intent data = new Intent();
        data.putExtra(MealsFragment.ID_KEY,position);
        data.putExtra(MealsFragment.MEALNAME,mProductName);
        data.putExtra(MealsFragment.ENERGY,mProductEnergy);
        data.putExtra(MealsFragment.PROTEIN,mProductProtein);
        data.putExtra(MealsFragment.FAT,mProductFat);
        data.putExtra(MealsFragment.CARBOHYDRATE,mProductCarbohydrate);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onCancelClick (View view){
        Log.d(TAG, "Cancel click");
        setResult(RESULT_CANCELED);
        finish();
    }
}
