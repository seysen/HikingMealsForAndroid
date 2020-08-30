package com.seysen.hikingmealsforandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seysen.hikingmealsforandroid.MealDetailActivity;
import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Meal;
import com.seysen.hikingmealsforandroid.core.MealProduct;
import com.seysen.hikingmealsforandroid.core.Product;
import com.seysen.hikingmealsforandroid.helper.CustomDialogFragment;
import com.seysen.hikingmealsforandroid.helper.Datable;
import com.seysen.hikingmealsforandroid.helper.MealAdapter;

import java.util.ArrayList;
import java.util.Objects;


public class MealsFragment extends Fragment implements Datable {

    public static final String ID_KEY = "meal_id";
    private static final String TAG = "meals_fragment";
    private static MealAdapter adapter;
    private RecyclerView mealList;
    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    public static final int REQUEST_CREATE_TYPE = 1;
    public static final int REQUEST_EDIT_TYPE = 2;
    public static final String MEALNAME = "meal";
    public static final String CARBOHYDRATE = "carbohydrate";
    public static final String ENERGY = "energy";
    public static final String PROTEIN = "protein";
    public static final String FAT = "fat";
    private static ArrayList<Meal> mMeals;

    public MealsFragment() {
        // Required empty public constructor
    }

    //todo update meal item on updating product
    public static void updateList(Meal meal) {
        Log.d(TAG,"Update List");
        int position = mMeals.indexOf(meal);
        Log.d(TAG,"Update position = " + position);
        adapter.notifyItemChanged(position);
        //adapter.notifyDataSetChanged();
    }

    public static void updateMeals(Product product) {
        Log.d(TAG,"Update meals with product " + product);
        for (Meal meal:
                mMeals) {
            for (MealProduct mealProduct:
                    meal.getMealProducts()) {
                if (mealProduct.getProduct().getProductName().equals(product.getProductName())) {
                    int position =  mMeals.indexOf(meal);
                    Log.d(TAG,"Update meal " + meal +" at position " + position);
                    meal.updateMealProducts();
                    mMeals.set(position,meal);
                    Log.d(TAG,"Update position = " + position);
                    adapter.notifyItemChanged(position);
                }
            }
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meals, container, false);
        mealList = view.findViewById(R.id.meals);
        mealList.setLayoutManager(new LinearLayoutManager(mealList.getContext()));
        //initMeals();
        mMeals = Meal.getMeals();
        adapter = new MealAdapter(getActivity(),mMeals);
        mealList.setAdapter(adapter);

        adapter.setOnItemClickListener(new MealAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent intent = new Intent(v.getContext(), MealDetailActivity.class);
                intent.putExtra(ID_KEY, position);
                intent.putExtra(MEALNAME, mMeals.get(position));
                Log.d(TAG, "Extras = " + String.valueOf(intent.getExtras()));
                startActivityForResult(intent, REQUEST_EDIT_TYPE);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
                String mealName = mMeals.get(position).getMealName();
                CustomDialogFragment dialog = new CustomDialogFragment();
                Bundle args = new Bundle();
                args.putString("item","meal");
                args.putString("name", mealName);
                args.putInt("position", position);
                dialog.setArguments(args);
                assert getFragmentManager() != null;
                dialog.show(getFragmentManager(),"custom");
            }
        });
        return view;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"On resume");
        adapter.notifyDataSetChanged();
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"OnActivityResult");
        if (requestCode==REQUEST_EDIT_TYPE) {
            Log.d(TAG, "Request edit");
            if(resultCode==RESULT_OK){
                Log.d(TAG, "Result OK");
                int position = data.getIntExtra(ID_KEY,0);
                Meal mMeal = data.getParcelableExtra(MEALNAME);
                Meal meal = Meal.getMeals().get(position);
                ArrayList<MealProduct> mealProducts = new ArrayList<>();
                assert mMeal != null;
                for (MealProduct mMealProduct:mMeal.getMealProducts()
                     ) {
                    Product mProduct = mMealProduct.getProduct();
                    double mWeight = mMealProduct.getWeight();
                    MealProduct mealProduct = new MealProduct(Product.getProduct(mProduct.getProductName()),mWeight);
                    mealProducts.add(mealProduct);
                }
                meal.setMealProducts(mealProducts);
                mMeals.set(position, meal);
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

    public static void addMeal() {
        //mMeals.add(mMeal);
        Log.d(TAG, "Notify data changed");
        adapter.notifyDataSetChanged();
    }

    public void removeItem (int position) {
        Log.d(TAG, "removeItem");
        mMeals.remove(position);
        adapter.notifyDataSetChanged();
    }

    /*private void initMeals() {
        if (mMeals.isEmpty()) {
            Meal pilaf = new Meal("Pilaf");
            pilaf.addMealProduct(new MealProduct(Product.getProduct("Rice"),100));
            pilaf.addMealProduct(new MealProduct(Product.getProduct("Onion"),30));
            pilaf.addMealProduct(new MealProduct(Product.getProduct("Carrot"),30));
            pilaf.addMealProduct(new MealProduct(Product.getProduct("Oil"),10));
            pilaf.addMealProduct(new MealProduct(Product.getProduct("Garlic"),2.5));
            pilaf.addMealProduct(new MealProduct(Product.getProduct("Mayonnaise"),10));
            pilaf.addMealProduct(new MealProduct(Product.getProduct("Ketchup"), 10));
            mMeals.add(pilaf);
            Meal porridge = new Meal("Porridge");
            porridge.addMealProduct(new MealProduct(Product.getProduct("Cereals"),70));
            porridge.addMealProduct(new MealProduct(Product.getProduct("Milk"), 25));
            porridge.addMealProduct(new MealProduct(Product.getProduct("Jam"),10));
            mMeals.add(porridge);
            Meal sandwich = new Meal("Sandwich");
            sandwich.addMealProduct(new MealProduct(Product.getProduct("Bread"),50));
            sandwich.addMealProduct(new MealProduct(Product.getProduct("Sausage"),80));
            mMeals.add(sandwich);
        }
    }*/

}
