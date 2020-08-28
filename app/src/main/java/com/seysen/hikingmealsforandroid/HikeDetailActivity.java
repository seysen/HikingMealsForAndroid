package com.seysen.hikingmealsforandroid;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.core.Hike;
import com.seysen.hikingmealsforandroid.core.HikeDay;
import com.seysen.hikingmealsforandroid.helper.Datable;
import com.seysen.hikingmealsforandroid.helper.HikeDayAdapter;
import com.seysen.hikingmealsforandroid.helper.MealDetailDialogFragment;
import com.seysen.hikingmealsforandroid.helper.SetNumbersDialogFragment;

import java.util.ArrayList;

public class HikeDetailActivity extends AppCompatActivity implements Datable, SetNumbersDialogFragment.SetNumberListener {

    private static final String TAG = "hike_detail";
    public static final String ID_KEY = "hike_id";
    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    public static final int REQUEST_CREATE_TYPE=1;
    public static final int REQUEST_EDIT_TYPE=2;
    private static final String HIKENAME = "hike";
    public static final String HIKEDAYNAME = "day";
    private int position;
    private HikeDayAdapter adapter;
    private Hike hike;
    private EditText hike_name;
    private Button duration;
    private Button quantity;
    private TextView hikeMealWeight;
    private TextView personMealWeight;
    private ArrayList<HikeDay> hikeDaysList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_detail);
        Log.d(TAG,"Hike Detail Created");
        hike_name = findViewById(R.id.hike_name_detail);
        duration = findViewById(R.id.day_btn);
        quantity = findViewById(R.id.persons_btn);
        hikeMealWeight = findViewById(R.id.hike_meal_weight);
        personMealWeight =  findViewById(R.id.person_meal_weight);
        RecyclerView hikeDays = findViewById(R.id.hike_days);
        hikeDays.setLayoutManager(new LinearLayoutManager(hikeDays.getContext()));
        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            Log.d(TAG,"Has arguments " + arguments);
            Log.d(TAG,"Get position with KEY = " + ID_KEY);
            position = arguments.getInt(ID_KEY);
            Log.d(TAG,"Position = " + position);
            hike = arguments.getParcelable(HIKENAME);
            Log.d(TAG,String.valueOf(hike));

        }
        hikeDaysList = hike.getHikeDays();
        adapter = new HikeDayAdapter(this, hikeDaysList);
        hikeDays.setAdapter(adapter);
        adapter.setOnItemClickListener(new HikeDayAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(v.getContext(),DayDetailActivity.class);
                intent.putExtra(ID_KEY, position);
                intent.putExtra(HIKEDAYNAME,hikeDaysList.get(position));
                startActivityForResult(intent,REQUEST_EDIT_TYPE);
            }
        });

        if (arguments!=null) {
            hike_name.setText(hike.getHikeName());
            duration.setText(String.format("%d days", hike.getDuration()));
            quantity.setText(String.format("%d persons", hike.getQuantity()));
            hikeMealWeight.setText(String.format("%.1f", hike.getWeight()*hike.getQuantity()).replace(",","."));
            personMealWeight.setText(String.format("%.1f", hike.getWeight()).replace(",","."));
            Log.d(TAG,"notifyDataSetChanged");
            hikeDaysList=hike.getHikeDays();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CREATE_TYPE: {

                }
                case REQUEST_EDIT_TYPE: {
                    assert data != null;
                    int position = data.getIntExtra(ID_KEY,0);
                    HikeDay day = data.getParcelableExtra(HIKEDAYNAME);
                    hikeDaysList.set(position,day);
                    hike.setHikeDays(hikeDaysList);
                    updateHike();
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    public void onOKClick (View view) {
        Intent data = new Intent();
        data.putExtra(ID_KEY, position);
        data.putExtra(HIKENAME,hike);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onCancelClick (View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onDayClick (View view) {
        int days = hike.getDuration();
        SetNumbersDialogFragment dialog = new SetNumbersDialogFragment();
        Bundle args = new Bundle();
        args.putString("string", "days");
        args.putInt("days", days);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(),"custom");
    }

    public void onPersonsClick (View view) {
        int persons = hike.getQuantity();
        SetNumbersDialogFragment dialog = new SetNumbersDialogFragment();
        Bundle args = new Bundle();
        args.putString("string", "persons");
        args.putInt("persons", persons);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(),"custom");
    }

    public void setDuration (int days) {
        hike.setDuration(days);
    }

    public void setQuantity (int persons) {
        hike.setQuantity(persons);
    }

    @Override
    public void removeItem(int position) {
        hikeDaysList.remove(position);
        adapter.notifyDataSetChanged();
        updateHike();
    }

    private void updateHike() {
        duration.setText(String.format("%d days", hike.getDuration()));
        quantity.setText(String.format("%d persons", hike.getQuantity()));
        hikeMealWeight.setText(String.format("%.1f", hike.getWeight()*hike.getQuantity()).replace(",","."));
        personMealWeight.setText(String.format("%.1f", hike.getWeight()).replace(",","."));
    }

    @Override
    public void onNumberSet(String key, int value) {
        switch (key) {
            case "days": {
                setDuration(value);
                adapter.notifyDataSetChanged();
                break;
            }
            case "persons": {
                setQuantity(value);
                break;
            }
        }
        updateHike();
    }
}