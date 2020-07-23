package com.seysen.hikingmealsforandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.seysen.hikingmealsforandroid.core.Product;
import com.seysen.hikingmealsforandroid.fragments.ProductsFragment;


public class ProductDetailActivity extends AppCompatActivity {

    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    private static final String TAG = "products_detail";
    private int position;
    private Product product;
    private EditText productName;
    private EditText productEnergy;
    private EditText productProtein;
    private EditText productFat;
    private EditText productCarbohydrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productName = findViewById(R.id.product_name_txt);
        productEnergy = findViewById(R.id.product_energy_txt);
        productProtein = findViewById(R.id.product_protein_txt);
        productFat = findViewById(R.id.product_fat_txt);
        productCarbohydrate = findViewById(R.id.product_carbohydrate_txt);

        Log.d(TAG, "Product detail created");

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {

            position = arguments.getInt(ProductsFragment.ID_KEY);
            Log.d(TAG,String.valueOf(position));
            product = getIntent().getParcelableExtra(ProductsFragment.PRODUCTNAME);
            assert product != null;
            productName.setText(product.getProductName());
            productEnergy.setText(String.format("%.1f", + product.getEnergy()).replace(",", "."));
            productProtein.setText(String.format("%.1f", + product.getProtein()).replace(",", "."));
            productFat.setText(String.format("%.1f", + product.getFat()).replace(",", "."));
            productCarbohydrate.setText(String.format("%.1f", + product.getCarbohydrate()).replace(",", "."));
        }
    }

    public void onOKClick (View view) {
        Log.d(TAG, "OK click");
        String mProductName = productName.getText().toString();
        double mProductEnergy = Double.parseDouble(productEnergy.getText().toString());
        double mProductProtein = Double.parseDouble(productProtein.getText().toString());
        double mProductFat = Double.parseDouble(productFat.getText().toString());
        double mProductCarbohydrate = Double.parseDouble(productCarbohydrate.getText().toString());
        Product mProduct = new Product(mProductName,mProductEnergy,mProductProtein,mProductFat,mProductCarbohydrate);
        Intent data = new Intent();
        data.putExtra(ProductsFragment.ID_KEY,position);
        data.putExtra(ProductsFragment.PRODUCTNAME,mProduct);
        setResult(RESULT_OK,data);
        Log.d(TAG, "Set result OK " + RESULT_OK);
        finish();
    }

    public void onCancelClick (View view){
        Log.d(TAG, "Cancel click");
        setResult(RESULT_CANCELED);
        finish();
    }
}
