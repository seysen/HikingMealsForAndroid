package com.seysen.hikingmealsforandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.core.HikeDay;
import com.seysen.hikingmealsforandroid.core.Meal;
import com.seysen.hikingmealsforandroid.helper.AddMealDialogFragment;
import com.seysen.hikingmealsforandroid.helper.Datable;
import com.seysen.hikingmealsforandroid.helper.DayMealsAdapter;
import com.seysen.hikingmealsforandroid.helper.MealDetailDialogFragment;

import java.util.ArrayList;

public class DayDetailActivity extends AppCompatActivity implements Datable, AddMealDialogFragment.MealReturnDialogListener {

    private static final String TAG = "day_detail";
    public static final String ID_KEY = "hike_id";
    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    public static final int REQUEST_CREATE_TYPE=1;
    public static final int REQUEST_EDIT_TYPE=2;
    public static final String HIKEDAYNAME = "day";
    public static final String MEALNAME = "meal";
    private int position;
    private DayMealsAdapter adapter;
    private HikeDay hikeDay;
    private TextView dayNumber;
    private TextView dayMealsNumber;
    private TextView dayWeight;
    private TextView dayCalories;
    private RecyclerView dayMeals;
    private ArrayList<Meal> meals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        dayNumber = findViewById(R.id.day_number_detail);
        dayMealsNumber = findViewById(R.id.day_meals_number_detail);
        dayWeight = findViewById(R.id.day_weight_detail);
        dayCalories = findViewById(R.id.day_calories_detail);
        dayMeals = findViewById(R.id.day_meals);
        dayMeals.setLayoutManager(new LinearLayoutManager(dayMeals.getContext()));
        adapter = new DayMealsAdapter(this,meals);
        dayMeals.setAdapter(adapter);
        adapter.setOnItemClickListener(new DayMealsAdapter.ClickListener() {
            @Override
            public void onItemLongClick(int position, View v) {
                String mealName = meals.get(position).getMealName();
                MealDetailDialogFragment dialog = new MealDetailDialogFragment();
                Bundle args = new Bundle();
                args.putString("item","meal");
                args.putString("name", mealName);
                args.putInt("position", position);
                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(),"custom");
            }
        });

        final FloatingActionButton fab = findViewById(R.id.day_detail_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMealDialogFragment dialogFragment = new AddMealDialogFragment();
                dialogFragment.show(getSupportFragmentManager(),"custom");
            }
        });

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            position = arguments.getInt(ID_KEY);
            hikeDay = arguments.getParcelable(HIKEDAYNAME);
            meals.clear();
            assert hikeDay != null;
            meals.addAll(hikeDay.getMeals());
            dayNumber.setText(String.valueOf(position+1));
            dayMealsNumber.setText(String.valueOf(meals.size()));
            dayWeight.setText(String.format("%.1f", + hikeDay.getDayWeight()).replace(",", "."));
            dayCalories.setText(String.format("%.1f", + hikeDay.getHikeDayEnergy()).replace(",", "."));
            adapter.notifyDataSetChanged();
        }
    }

    public void onOKClick (View view) {
        Intent data = new Intent();
        data.putExtra(ID_KEY, position);
        data.putExtra(HIKEDAYNAME,hikeDay);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onCancelClick (View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void removeItem(int position) {
        meals.remove(position);
        adapter.notifyItemInserted(position);
        updateHikeDay();
    }

    private void updateHikeDay() {
        hikeDay.setMeals(meals);
        dayMealsNumber.setText(String.valueOf(meals.size()));
        dayWeight.setText(String.format("%.1f", + hikeDay.getDayWeight()).replace(",", "."));
        dayCalories.setText(String.format("%.1f", + hikeDay.getHikeDayEnergy()).replace(",", "."));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogItemPick(Meal meal) {
        meals.add(meal);
        adapter.notifyItemInserted(meals.size()-1);
        updateHikeDay();
    }
}