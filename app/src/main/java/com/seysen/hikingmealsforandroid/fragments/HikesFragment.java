package com.seysen.hikingmealsforandroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Hike;
import com.seysen.hikingmealsforandroid.helper.HikeAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class HikesFragment extends Fragment {

    public static final String ID_KEY = "hike_id";
    private static final String TAG = "hikes_fragment";
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hikes, container, false);
        hikeList = view.findViewById(R.id.hikes);
        hikeList.setLayoutManager(new LinearLayoutManager(hikeList.getContext()));
        //initHikes();
        mHikes = Hike.getHikes();
        adapter = new HikeAdapter(Objects.requireNonNull(getActivity()),mHikes);
        hikeList.setAdapter(adapter);

        adapter.setOnItemClickListener(new HikeAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        return view;
    }

    private void initHikes() {

    }
}
