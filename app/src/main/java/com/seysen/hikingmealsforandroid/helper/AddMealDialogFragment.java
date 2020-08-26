package com.seysen.hikingmealsforandroid.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.seysen.hikingmealsforandroid.R;
import com.seysen.hikingmealsforandroid.core.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddMealDialogFragment extends DialogFragment {
    private MealReturnDialogListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ArrayList<Meal> mealsArray = Meal.getMeals();
        final ArrayAdapter<Meal> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, mealsArray);
        builder.setTitle("Add meal").setAdapter( adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogItemPick(adapter.getItem(which));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (MealReturnDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MealReturnDialogListener");
        }
    }

    public interface MealReturnDialogListener {
        void onDialogItemPick(Meal meal);
    }
}
