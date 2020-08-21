package com.seysen.hikingmealsforandroid.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.HikeDay;

import java.util.ArrayList;

public class HikeDayAdapter extends RecyclerView.Adapter<HikeDayAdapter.HikeDayViewHolder> {
    private final static String TAG = "hike_day_adapter";
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private LayoutInflater inflater;
    private ArrayList<HikeDay> mHikeDays;

    public HikeDayAdapter.HikeDayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hike_day_item, viewGroup, false);
        return new HikeDayAdapter.HikeDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikeDayViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder");
        HikeDay day = mHikeDays.get(position);
        holder.hikeDayNumberView.setText(String.valueOf(position+1));
        holder.hikeDayMealsView.setText(String.valueOf(day.getMeals().size()));
        holder.hikeDayWeight.setText(String.format("%.1f", + day.getDayWeight()).replace(",", "."));
        holder.hikeDayEnergy.setText(String.format("%.1f", + day.getHikeDayEnergy()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return mHikeDays.size();
    }

    public HikeDayAdapter(Context context, ArrayList<HikeDay> items) {
        Log.d(TAG,"HikeDayAdapter created with items " + items);
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mHikeDays = items;
    }

    public static class HikeDayViewHolder extends RecyclerView.ViewHolder {

        private final TextView hikeDayNumberView;
        private final TextView hikeDayMealsView;
        private final TextView hikeDayWeight;
        private final TextView hikeDayEnergy;

        public HikeDayViewHolder(@NonNull View itemView) {
            super(itemView);
            hikeDayNumberView = itemView.findViewById(R.id.day_number);
            hikeDayMealsView = itemView.findViewById(R.id.nomber_of_meals);
            hikeDayWeight = itemView.findViewById(R.id.day_weight);
            hikeDayEnergy = itemView.findViewById(R.id.day_energy);
        }
    }
}
