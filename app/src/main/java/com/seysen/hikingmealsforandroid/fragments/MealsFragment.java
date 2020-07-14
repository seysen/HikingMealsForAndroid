package com.seysen.hikingmealsforandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.MealDetailActivity;
import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Meal;
import com.seysen.hikingmealsforandroid.core.Product;
import com.seysen.hikingmealsforandroid.helper.CustomDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class MealsFragment extends Fragment {

    public static final String ID_KEY = "meal_id";
    private static final String TAG = "meal_fragment";
    private RecyclerView mealList;
    private MealsFragment.MealAdapter adapter;
    public static final int REQUEST_CREATE_TYPE=1;
    public static final int REQUEST_EDIT_TYPE=2;
    public static final int RESULT_OK = -1;
    public static final String MEALNAME = "product";
    public static final String CARBOHYDRATE = "carbohydrate";
    public static final String ENERGY = "energy";
    public static final String PROTEIN = "protein";
    public static final String FAT = "fat";
    private static ArrayList<Meal> mMeals = new ArrayList<Meal>();
    //private HashMap<Product, Double> mealProducts = new HashMap<Product, Double>();

    public MealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meals, container, false);
        mealList = view.findViewById(R.id.meals);
        mealList.setLayoutManager(new LinearLayoutManager(mealList.getContext()));
        initMeals();
        adapter = new MealsFragment.MealAdapter(getActivity(),mMeals);
        mealList.setAdapter(adapter);

        adapter.setOnItemClickListener(new MealsFragment.MealAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent intent = new Intent(v.getContext(), MealDetailActivity.class);
                intent.putExtra(ID_KEY, position);
                /*intent.putExtra(MEALNAME,mMeals.get(position).getMealName());
                intent.putExtra(ENERGY,mMeals.get(position).getEnergy());
                intent.putExtra(PROTEIN,mMeals.get(position).getProtein());
                intent.putExtra(FAT,mMeals.get(position).getFat());
                intent.putExtra(CARBOHYDRATE,mMeals.get(position).getCarbohydrate());*/
                intent.putExtra("MEAL",mMeals.get(position));
                intent.putExtra("PRODUCTS",mMeals.get(position).getMealProducts());
                startActivityForResult(intent, REQUEST_EDIT_TYPE);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
                String productName = mMeals.get(position).getMealName();
                CustomDialogFragment dialog = new CustomDialogFragment();

                Bundle args = new Bundle();
                args.putString("item","meal");
                args.putString("name", productName);
                args.putInt("position", position);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"custom");
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CREATE_TYPE){
            Log.d(TAG, "Request create");
            if(resultCode==RESULT_OK){
                Log.d(TAG, "Result OK");
                /*String name = data.getStringExtra(MEALNAME);
                double energy = data.getDoubleExtra(ENERGY, 0.0);
                double protein = data.getDoubleExtra(PROTEIN, 0.0);
                double fat = data.getDoubleExtra(FAT, 0.0);
                double carbohydrate = data.getDoubleExtra(CARBOHYDRATE, 0.0);
                mMeals.add(new Meal(name, energy, protein, fat, carbohydrate));*/

            }
            else{
                Log.d(TAG, "Create canceled");
            }
        }
        else if (requestCode==REQUEST_EDIT_TYPE) {
            Log.d(TAG, "Request edit");
            if(resultCode==RESULT_OK){
                Log.d(TAG, "Result OK");
                /*int position = data.getIntExtra(ID_KEY,0);
                String name = data.getStringExtra(MEALNAME);
                double energy = data.getDoubleExtra(ENERGY, 0.0);
                double protein = data.getDoubleExtra(PROTEIN, 0.0);
                double fat = data.getDoubleExtra(FAT, 0.0);
                double carbohydrate = data.getDoubleExtra(CARBOHYDRATE, 0.0);
                Product product = new Product();
                product.setProductName(name);
                product.setEnergy(energy);
                product.setProtein(protein);
                product.setFat(fat);
                product.setCarbohydrate(carbohydrate);
                mMeals.set(position,product);*/
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

    private void initMeals() {
        if (mMeals.isEmpty()) {
            /*Meal pilaf = new Meal("Pilaf");
            pilaf.addMealProduct(new Product("Rice", 374, 7.51, 1.03, 80.89),100);
            pilaf.addMealProduct(new Product("Onion", 47, 1.4, 0, 10.4),30);
            pilaf.addMealProduct(new Product("Carrot", 32, 1.3, 0.1, 6.9),30);
            pilaf.addMealProduct(new Product("Oil", 899, 0, 99, 0),10);
            pilaf.addMealProduct(new Product("Garlic", 134, 6.5, 0.5, 29.9),2.5);
            pilaf.addMealProduct(new Product("Mayonnaise", 624, 3.1, 67, 2.6),10);
            pilaf.addMealProduct(new Product("Ketchup", 93, 1.8, 1, 22.2), 10);
            mMeals.add(pilaf);
            Meal porridge = new Meal("Porridge");
            porridge.addMealProduct(new Product("Cereals", 414, 3.45, 3.45, 89.66),70);
            porridge.addMealProduct(new Product("Milk", 362, 33.2, 1, 52.6), 25);
            porridge.addMealProduct(new Product("Jam", 226, 0, 0, 56.5),10);
            mMeals.add(porridge);
            Meal sandwich = new Meal("Sandwich");
            sandwich.addMealProduct(new Product("Bread", 264, 7.5, 2.9, 50.9),50);
            sandwich.addMealProduct(new Product("Sausage", 461, 24, 40.5, 0),80);
            mMeals.add(sandwich);*/
        }
    }

    public static class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealsViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private LayoutInflater inflater;
        private ArrayList<Meal> meals;
        private static ClickListener clickListener;

        @NonNull
        @Override
        public MealsFragment.MealAdapter.MealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.meal_item, viewGroup, false);
            return new MealsFragment.MealAdapter.MealsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MealsFragment.MealAdapter.MealsViewHolder holder, int position) {
            Meal meal = mMeals.get(position);
            holder.nameView.setText(meal.getMealName());
            holder.energyView.setText(String.format("%.1f", + meal.getEnergy()).replace(",", "."));
            holder.proteinView.setText(String.format("%.1f", +meal.getProtein()).replace(",", "."));
            holder.fatView.setText(String.format("%.1f", + meal.getFat()).replace(",", "."));
            holder.carbohydrateView.setText(String.format("%.1f", + meal.getCarbohydrate()).replace(",", "."));
        }

        @Override
        public int getItemCount() {
            return mMeals.size();
        }

        public MealAdapter(Context context, ArrayList<Meal> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mMeals = items;
        }

        public static class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            private final TextView nameView;
            private final TextView energyView;
            private final TextView proteinView;
            private final TextView fatView;
            private final TextView carbohydrateView;

            public MealsViewHolder(@NonNull View view) {
                super(view);
                nameView = (TextView)view.findViewById(R.id.mealName);
                energyView = (TextView)view.findViewById(R.id.meal_energy);
                proteinView = (TextView)view.findViewById(R.id.meal_protein);
                fatView = (TextView)view.findViewById(R.id.meal_fat);
                carbohydrateView = (TextView)view.findViewById(R.id.meal_carbohydrate);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }

            @Override
            public void onClick(View v) {
                clickListener.onItemClick(getAdapterPosition(), v);
            }

            @Override
            public boolean onLongClick(View v) {
                clickListener.onItemLongClick(getAdapterPosition(), v);
                return false;
            }
        }

        public void setOnItemClickListener(ClickListener clickListener) {
            MealAdapter.clickListener = clickListener;
        }

        public interface ClickListener {
            void onItemClick(int position, View v);
            void onItemLongClick(int position, View v);
        }
    }
}
