package com.seysen.hikingmealsforandroid.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import com.seysen.hikingmealsforandroid.R;

import java.util.Objects;

public class SetNumbersDialogFragment extends DialogFragment {
    SetNumberListener mListener;
    private int mValue;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        final String key = getArguments().getString("string");
        final int value = getArguments().getInt(key);
        mValue = value;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_number_picker,null);
        NumberPicker picker = view.findViewById(R.id.number_picker);
        picker.setMinValue(1);
        picker.setMaxValue(50);
        picker.setValue(value);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mValue = newVal;
            }
        });
        builder.setView(view).setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onNumberSet(key,mValue);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (SetNumberListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement SetNumberListener");
        }
    }

    public interface SetNumberListener {
        void onNumberSet(String key, int value);
    }
}
