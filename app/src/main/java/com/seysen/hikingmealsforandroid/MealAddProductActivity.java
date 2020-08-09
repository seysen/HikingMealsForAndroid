package com.seysen.hikingmealsforandroid;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.seysen.hikingmealsforandroid.core.MealProduct;
import com.seysen.hikingmealsforandroid.core.Product;

import static com.seysen.hikingmealsforandroid.MealDetailActivity.ID_KEY;
import static com.seysen.hikingmealsforandroid.MealDetailActivity.PRODUCTNAME;

public class MealAddProductActivity extends AppCompatActivity {

    private static final String TAG = "AddProduct";
    private Spinner spinner;
    private EditText weight;
    private ArrayAdapter<Product> adapter;
    private int position;
    private MealProduct product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_add_product);
        spinner = findViewById(R.id.products);
        weight = findViewById(R.id.product_weight);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Product.getProducts());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            Log.d(TAG,"Has arguments " + arguments);
            position = arguments.getInt(ID_KEY);
            Log.d(TAG,"Position = " + position);
            product = getIntent().getParcelableExtra(PRODUCTNAME);
            Log.d(TAG,"Product = " + product);


        }
    }

    public void onOKClick (View view) {
        Log.d(TAG, "OK click");

    }

    public void onCancelClick (View view) {
        Log.d(TAG, "Cancel click");
        setResult(RESULT_CANCELED);
        finish();
    }
}
