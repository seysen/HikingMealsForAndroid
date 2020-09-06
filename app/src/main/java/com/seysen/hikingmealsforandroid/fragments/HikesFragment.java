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

import com.seysen.hikingmealsforandroid.HikeDetailActivity;
import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Hike;
import com.seysen.hikingmealsforandroid.helper.CustomDialogFragment;
import com.seysen.hikingmealsforandroid.helper.Datable;
import com.seysen.hikingmealsforandroid.helper.HikeAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class HikesFragment extends Fragment implements Datable {

    public static final String ID_KEY = "hike_id";
    private static final String TAG = "hikes_fragment";
    private static final String HIKENAME = "hike";
    private static HikeAdapter adapter;
    private RecyclerView hikeList;
    private static final int RESULT_OK = 0;
    private static final int RESULT_CANCELED = 1;
    public static final int REQUEST_CREATE_TYPE = 1;
    public static final int REQUEST_EDIT_TYPE = 2;
    private static ArrayList<Hike> mHikes;

    public HikesFragment() {
        // Required empty public constructor
    }

    public static void addHike() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hikes, container, false);
        hikeList = view.findViewById(R.id.hikes);
        hikeList.setLayoutManager(new LinearLayoutManager(hikeList.getContext()));
        mHikes = Hike.getHikes();
        adapter = new HikeAdapter(Objects.requireNonNull(getActivity()),mHikes);
        hikeList.setAdapter(adapter);

        adapter.setOnItemClickListener(new HikeAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent intent = new Intent(v.getContext(), HikeDetailActivity.class);
                intent.putExtra(ID_KEY, position);
                intent.putExtra(HIKENAME, mHikes.get(position));
                //Log.d(TAG, "Extras = " + String.valueOf(intent.getExtras()));
                startActivityForResult(intent, REQUEST_EDIT_TYPE);
            }

            @Override
            public void onItemLongClick(int position, View v) {Log.d(TAG, "onItemLongClick pos = " + position);
                String hikeName = mHikes.get(position).getHikeName();
                CustomDialogFragment dialog = new CustomDialogFragment();
                Bundle args = new Bundle();
                args.putString("item","hike");
                args.putString("name", hikeName);
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
        if (resultCode==RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CREATE_TYPE: {

                }
                case REQUEST_EDIT_TYPE: {
                    int position = data.getIntExtra(ID_KEY,0);
                    Hike hike = data.getParcelableExtra(HIKENAME);
                    mHikes.set(position,hike);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void removeItem(int position) {
        Log.d(TAG, "removeItem");
        mHikes.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
