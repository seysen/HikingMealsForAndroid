package com.seysen.hikingmealsforandroid.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.seysen.hikingmealsforandroid.helper.Datable;

public class CustomDialogFragment extends DialogFragment {

    private Datable datable;
    private static final String TAG = "custom_dialog";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        //datable = (Datable) context;
        //Log.d(TAG, "datable");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final String item = getArguments().getString("item");
        final String name = getArguments().getString("name");
        final int position = getArguments().getInt("position");
        Log.d(TAG, "onCreateDialog");
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
