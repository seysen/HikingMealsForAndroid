package com.seysen.hikingmealsforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.seysen.hikingmealsforandroid.core.Product;

public class MealAddProductActivity extends AppCompatActivity {

    private static final String TAG = "AddProduct";
    private Spinner spinner;
    private EditText weight;
    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_add_product);
        spinner = (Spinner) findViewById(R.id.products);
        weight = (EditText) findViewById(R.id.product_weight);
        adapter = new ArrayAdapter<Product>(this,android.R.layout.simple_spinner_item,Product.getProducts());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
