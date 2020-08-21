package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class HikeDay implements Parcelable {
    //variables
    private final static String TAG = "HikeDay";
    private ArrayList<Meal> meals = new ArrayList<Meal>();

    protected HikeDay(Parcel in) {
        meals = in.createTypedArrayList(Meal.CREATOR);
    }

    public static final Creator<HikeDay> CREATOR = new Creator<HikeDay>() {
        @Override
        public HikeDay createFromParcel(Parcel in) {
            return new HikeDay(in);
        }

        @Override
        public HikeDay[] newArray(int size) {
            return new HikeDay[size];
        }
    };

    //methods
    public void addMeal(Meal meal) {
        if (meal!=null) {
            meals.add(meal);
        }
    }

    public void removeMeal(int index) {
        meals.remove(index);
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public double getHikeDayEnergy() {
        double energy = 0;
        for (Meal meal : meals) {
            energy += meal.getEnergy();
        }
        return energy;
    }

    //constructors

    public HikeDay() {

    }

    public HikeDay(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(meals);
    }

    public double getDayWeight() {
        double weight = 0;
        for (Meal meal: meals
             ) {
            weight += meal.getWeight();
        }
        return weight;
    }
}
