package com.seysen.hikingmealsforandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.seysen.hikingmealsforandroid.core.Meal;
import com.seysen.hikingmealsforandroid.core.MealProduct;
import com.seysen.hikingmealsforandroid.core.Product;

import static com.seysen.hikingmealsforandroid.MealDetailActivity.ID_KEY;
import static com.seysen.hikingmealsforandroid.MealDetailActivity.PRODUCTNAME;

public class MealAddProductActivity extends AppCompatActivity {

    private static final String TAG = "AddProduct";
    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    private Spinner spinner;
    private EditText weight;
    private ArrayAdapter<Product> adapter;
    private int position;
    private MealProduct mealProduct;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_add_product);
        spinner = findViewById(R.id.products);

        weight = findViewById(R.id.product_weight);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Product.getProducts());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product = Product.getProducts().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            Log.d(TAG,"Has arguments " + arguments);
            position = arguments.getInt(ID_KEY);
            Log.d(TAG,"Position = " + position);
            mealProduct = getIntent().getParcelableExtra(PRODUCTNAME);
            Log.d(TAG,"Product = " + product);
            product = mealProduct.getProduct();
            spinner.setSelection(Product.getProducts().indexOf(product));
            weight.setText(String.valueOf(mealProduct.getWeight()));
        }
    }

    public void onOKClick (View view) {
        Log.d(TAG, "OK click");
        MealProduct mealProduct = new MealProduct(product, weight.getText().toString().equals("")? 0.0: Double.parseDouble(weight.getText().toString()));
        Intent data = new Intent();
        data.putExtra(ID_KEY,position);
        data.putExtra(PRODUCTNAME,mealProduct);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onCancelClick (View view) {
        Log.d(TAG, "Cancel click");
        setResult(RESULT_CANCELED);
        finish();
    }
}
