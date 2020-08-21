package com.seysen.hikingmealsforandroid.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Hike;

import java.util.ArrayList;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.HikeViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private LayoutInflater inflater;
    private ArrayList<Hike> hikes;
    private static HikeAdapter.ClickListener clickListener;

    @NonNull
    @Override
    public HikeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hike_item, viewGroup, false);
        return new HikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikeViewHolder hikeViewHolder, int i) {
        Hike hike = hikes.get(i);
        hikeViewHolder.hikeView.setText(hike.getHikeName());
        hikeViewHolder.durationView.setText(String.format("%d", + hike.getDuration()).replace(",", "."));
        hikeViewHolder.quantityView.setText(String.format("%d", + hike.getQuantity()).replace(",", "."));
        hikeViewHolder.weightView.setText(String.format("%.1f", + hike.getWeight()).replace(",", "."));
        hikeViewHolder.personWeightView.setText(String.format("%.1f", + hike.getWeight()/hike.getQuantity()).replace(",", "."));
    }

    @Override
    public int getItemCount() {
        return hikes.size();
    }

    public HikeAdapter(Context context, ArrayList<Hike> hikes) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground,mTypedValue,true);
        mBackground = mTypedValue.resourceId;
        this.hikes = hikes;
    }

    public static class HikeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView hikeView;
        private final TextView durationView;
        private final TextView quantityView;
        private final TextView weightView;
        private final TextView personWeightView;

        public HikeViewHolder(@NonNull View itemView) {
            super(itemView);
            hikeView = itemView.findViewById(R.id.hike_name);
            durationView = itemView.findViewById(R.id.hike_duaration);
            quantityView = itemView.findViewById(R.id.hike_quantity);
            weightView = itemView.findViewById(R.id.hike_weight);
            personWeightView = itemView.findViewById(R.id.person_weight);
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

    public void setOnItemClickListener(HikeAdapter.ClickListener clickListener) {
        HikeAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
