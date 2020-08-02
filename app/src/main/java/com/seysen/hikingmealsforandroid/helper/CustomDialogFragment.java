package com.seysen.hikingmealsforandroid.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.seysen.hikingmealsforandroid.MainActivity;

import java.util.Objects;

public class CustomDialogFragment extends DialogFragment {

    private int selectedPage;
    private Datable datable;
    private Fragment fragment = null;
    private static final String TAG = "custom_dialog";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        fragment = ((MainActivity) Objects.requireNonNull(getActivity())).getActiveFragment();
        datable = (Datable) fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog");
        assert getArguments() != null;
        final String item = getArguments().getString("item");
        final String name = getArguments().getString("name");
        final int position = getArguments().getInt("position");
        Log.d(TAG, "datable = " + datable);
        return new AlertDialog.Builder(getActivity())
                .setTitle("Remove " + item + "?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Do you want to remove "  + item + " " + name + "?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datable.removeItem(position);
                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
    }
}
