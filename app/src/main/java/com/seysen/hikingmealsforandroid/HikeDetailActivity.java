package com.seysen.hikingmealsforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Hike;
import com.seysen.hikingmealsforandroid.core.HikeDay;
import com.seysen.hikingmealsforandroid.helper.HikeDayAdapter;

import java.util.ArrayList;

public class HikeDetailActivity extends AppCompatActivity {

    private static final String TAG = "hike_detail";
    public static final String ID_KEY = "hike_id";
    private static final int RESULT_CANCELED = 1;
    public static final int REQUEST_CREATE_TYPE=1;
    public static final int REQUEST_EDIT_TYPE=2;
    private static final String HIKENAME = "hike";
    public static final String HIKEDAYNAME = "day";
    private int position;
    private HikeDayAdapter adapter;
    private Hike hike;
    private EditText hike_name;
    private TextView duration;
    private TextView quantity;
    private TextView hikeMealWeight;
    private TextView personMealWeight;
    private ArrayList<HikeDay> hikeDaysList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_detail);
        Log.d(TAG,"Hike Detail Created");
        hike_name = findViewById(R.id.hike_name_detail);
        duration = findViewById(R.id.duaration);
        quantity = findViewById(R.id.quantity);
        hikeMealWeight = findViewById(R.id.hike_meal_weight);
        personMealWeight =  findViewById(R.id.person_meal_weight);
        RecyclerView hikeDays = findViewById(R.id.hike_days);
        hikeDays.setLayoutManager(new LinearLayoutManager(hikeDays.getContext()));
        adapter = new HikeDayAdapter(this, hikeDaysList);
        hikeDays.setAdapter(adapter);

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            Log.d(TAG,"Has arguments " + arguments);
            Log.d(TAG,"Get position with KEY = " + ID_KEY);
            position = arguments.getInt(ID_KEY);
            Log.d(TAG,"Position = " + position);
            hike = arguments.getParcelable(HIKENAME);
            Log.d(TAG,String.valueOf(hike));
            //mHikeDays = hike.getHikeDays();
            hike_name.setText(hike.getHikeName());
            duration.setText(String.valueOf(hike.getDuration()));
            quantity.setText(String.valueOf(hike.getQuantity()));
            hikeMealWeight.setText(String.format("%.1f", hike.getWeight()).replace(",","."));
            personMealWeight.setText(String.format("%.1f", hike.getWeight()/hike.getQuantity()).replace(",","."));
            Log.d(TAG,"notifyDataSetChanged");
            hikeDaysList.clear();
            hikeDaysList.addAll(hike.getHikeDays());
            adapter.notifyDataSetChanged();
        }

    }
}