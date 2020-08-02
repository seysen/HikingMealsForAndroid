package com.seysen.hikingmealsforandroid.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seysen.hikingmealsforandroid.helper.CustomDialogFragment;
import com.seysen.hikingmealsforandroid.helper.Datable;
import com.seysen.hikingmealsforandroid.ProductDetailActivity;
import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Product;
import com.seysen.hikingmealsforandroid.helper.ProductAdapter;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements Datable {

    public static final String ID_KEY = "product_id";
    private static final String TAG = "products_fragment";
    private RecyclerView productList;
    private static ProductAdapter adapter;
    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    public static final int REQUEST_CREATE_TYPE = 1;
    public static final int REQUEST_EDIT_TYPE = 2;
    public static final String PRODUCTNAME = "product";
    public static final String CARBOHYDRATE = "carbohydrate";
    public static final String ENERGY = "energy";
    public static final String PROTEIN = "protein";
    public static final String FAT = "fat";
    private static ArrayList<Product> mProducts = new ArrayList<>();

    public ProductsFragment() {
        // Required empty public constructor
    }

    public static void addProduct(Product mProduct) {
        mProducts.add(mProduct);
        Log.d(TAG, "Notify data changed");
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG,"Products Fragment Created");
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        productList = view.findViewById(R.id.products);
        productList.setLayoutManager(new LinearLayoutManager(productList.getContext()));
        initProducts();
        adapter = new ProductAdapter(Objects.requireNonNull(getActivity()),mProducts);
        productList.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Log.d(TAG, "View = " + v);
                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                intent.putExtra(ID_KEY, position);
                intent.putExtra(PRODUCTNAME,mProducts.get(position));
                startActivityForResult(intent, REQUEST_EDIT_TYPE);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
                String productName = mProducts.get(position).getProductName();
                CustomDialogFragment dialog = new CustomDialogFragment();
                Bundle args = new Bundle();
                args.putString("item","product");
                args.putString("name", productName);
                args.putInt("position", position);
                dialog.setArguments(args);
                assert getFragmentManager() != null;
                dialog.show(getFragmentManager(),"custom");
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"OnActivityResult");
        if(requestCode==REQUEST_CREATE_TYPE){
            Log.d(TAG, "Request create");
            if(resultCode==0){
                Log.d(TAG, "Result OK");
                Product mProduct = data.getParcelableExtra(PRODUCTNAME);
                mProducts.add(mProduct);
            }
            else{
                Log.d(TAG, "Create canceled");
            }
        }
        else if (requestCode==REQUEST_EDIT_TYPE) {
            Log.d(TAG, "Request edit");
            if(resultCode==RESULT_OK){
                Log.d(TAG, "Result OK");
                int position = data.getIntExtra(ID_KEY,0);
                Product mProduct = data.getParcelableExtra(PRODUCTNAME);
                mProducts.add(position,mProduct);
            }
            else{
                Log.d(TAG, "Edit canceled");
            }
        } else {
            Log.d(TAG, "Result super");
            super.onActivityResult(requestCode, resultCode, data);
        }
        Log.d(TAG, "Notify data changed");
        adapter.notifyDataSetChanged();
    }

    public void removeItem (int position) {
        Log.d(TAG, "removeItem");
        mProducts.remove(position);
        adapter.notifyDataSetChanged();
    }

    /*public void save(View view) {
        boolean result = JSONHelper.exportToJSON(this, products);
        if (result) {
            Log.d(TAG, "Data saved");
        } else {
            Log.d(TAG, "Data not saved");
        }
    }

    public void open(View view) {
        products = JSONHelper.importFromJSON(this);
        if (products!=null) {
            Log.d(TAG, "Data loaded");
        } else {
            Log.d(TAG, "Data not loaded");
        }
    }*/

    private void initProducts() {
        if (mProducts.isEmpty()) {
            Log.d(TAG,"Init Products");
            mProducts.add(new Product("Pasta", 350, 13, 1.5, 72));
            mProducts.add(new Product("Rice", 374, 7.51, 1.03, 80.89));
            mProducts.add(new Product("Buckwheat", 340, 10.9, 2.7, 71.3));
            mProducts.add(new Product("Lentils", 352, 24.63, 1.06, 63.35));
            mProducts.add(new Product("Cereals", 414, 3.45, 3.45, 89.66));
            mProducts.add(new Product("Ketchup", 93, 1.8, 1, 22.2));
            mProducts.add(new Product("Mayonnaise", 624, 3.1, 67, 2.6));
            mProducts.add(new Product("Jam", 226, 0, 0, 56.5));
            mProducts.add(new Product("Beef", 232, 16.8, 18.4, 0));
            mProducts.add(new Product("Milk", 362, 33.2, 1, 52.6));
            mProducts.add(new Product("Onion", 47, 1.4, 0, 10.4));
            mProducts.add(new Product("Carrot", 32, 1.3, 0.1, 6.9));
            mProducts.add(new Product("Garlic", 134, 6.5, 0.5, 29.9));
            mProducts.add(new Product("Oil", 899, 0, 99, 0));
            mProducts.add(new Product("Cheese", 330, 24, 26, 0));
            mProducts.add(new Product("Chicken", 110, 23.1, 1.2, 0));
            mProducts.add(new Product("Bread", 264, 7.5, 2.9, 50.9));
            mProducts.add(new Product("Sausage", 461, 24, 40.5, 0));
        }
    }

}
