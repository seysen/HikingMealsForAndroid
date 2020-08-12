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
    private static ArrayList<Meal> mMeals = new ArrayList<>();

    public MealsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meals, container, false);
        mealList = view.findViewById(R.id.meals);
        mealList.setLayoutManager(new LinearLayoutManager(mealList.getContext()));
        initMeals();
        adapter = new MealAdapter(Objects.requireNonNull(getActivity()),mMeals);
        mealList.setAdapter(adapter);

        adapter.setOnItemClickListener(new MealAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent intent = new Intent(v.getContext(), MealDetailActivity.class);
                intent.putExtra(ID_KEY, position);
                intent.putExtra(MEALNAME, mMeals.get(position));
                Log.d(TAG, String.valueOf(intent));
                Log.d(TAG, String.valueOf(intent.getIntExtra(ID_KEY,0)));
                Log.d(TAG, String.valueOf(intent.getExtras()));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"OnActivityResult");
        if (requestCode==REQUEST_EDIT_TYPE) {
            Log.d(TAG, "Request edit");
            if(resultCode==RESULT_OK){
                Log.d(TAG, "Result OK");
                int position = data.getIntExtra(ID_KEY,0);
                Log.d(TAG, "Position = " + position);
                Meal meal = data.getParcelableExtra(MEALNAME);
                mMeals.set(position,meal);
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

    public static void addMeal(Meal mMeal) {
        mMeals.add(mMeal);
        Log.d(TAG, "Notify data changed");
        adapter.notifyDataSetChanged();
    }

    public void removeItem (int position) {
        Log.d(TAG, "removeItem");
        mMeals.remove(position);
        adapter.notifyDataSetChanged();
    }

    private void initMeals() {
        if (mMeals.isEmpty()) {
            Meal pilaf = new Meal("Pilaf");
            pilaf.addMealProduct(new MealProduct(new Product("Rice", 374, 7.51, 1.03, 80.89),100));
            pilaf.addMealProduct(new MealProduct(new Product("Onion", 47, 1.4, 0, 10.4),30));
            pilaf.addMealProduct(new MealProduct(new Product("Carrot", 32, 1.3, 0.1, 6.9),30));
            pilaf.addMealProduct(new MealProduct(new Product("Oil", 899, 0, 99, 0),10));
            pilaf.addMealProduct(new MealProduct(new Product("Garlic", 134, 6.5, 0.5, 29.9),2.5));
            pilaf.addMealProduct(new MealProduct(new Product("Mayonnaise", 624, 3.1, 67, 2.6),10));
            pilaf.addMealProduct(new MealProduct(new Product("Ketchup", 93, 1.8, 1, 22.2), 10));
            mMeals.add(pilaf);
            Meal porridge = new Meal("Porridge");
            porridge.addMealProduct(new MealProduct(new Product("Cereals", 414, 3.45, 3.45, 89.66),70));
            porridge.addMealProduct(new MealProduct(new Product("Milk", 362, 33.2, 1, 52.6), 25));
            porridge.addMealProduct(new MealProduct(new Product("Jam", 226, 0, 0, 56.5),10));
            mMeals.add(porridge);
            Meal sandwich = new Meal("Sandwich");
            sandwich.addMealProduct(new MealProduct(new Product("Bread", 264, 7.5, 2.9, 50.9),50));
            sandwich.addMealProduct(new MealProduct(new Product("Sausage", 461, 24, 40.5, 0),80));
            mMeals.add(sandwich);
        }
    }

}
