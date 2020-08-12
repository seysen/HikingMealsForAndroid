package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class Meal implements Parcelable {
    private static final String TAG = "meal_java";

    private static ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<MealProduct> mealProducts;

    private String mealName;
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrate;
    private double weight;

    //Constructors

    public Meal(String mealName, ArrayList<MealProduct> mealProducts) {
        this.mealName = mealName;
        this.mealProducts = mealProducts;
        this.update();
    }

    public Meal(String mealName) {
        this(mealName,new ArrayList<MealProduct>());
    }

    //Parcelable implementation

    protected Meal(Parcel in) {
        mealName = in.readString();
        mealProducts = in.createTypedArrayList(MealProduct.CREATOR);
        this.update();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mealName);
        dest.writeTypedList(mealProducts);
    }

    //Methods

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.mealName;
    }

    public static ArrayList<Meal> getMeals() {
        return meals;
    }

    public static void setMeals(ArrayList<Meal> meals) {
        Meal.meals = meals;
    }

    public ArrayList<MealProduct> getMealProducts() {
        return mealProducts;
    }

    public void setMealProducts(ArrayList<MealProduct> mealProducts) {
        this.mealProducts = mealProducts;
        this.update();
    }

    private void update() {
        this.energy = 0;
        this.protein = 0;
        this.fat = 0;
        this.carbohydrate = 0;
        this.weight = 0;
        for (MealProduct product: mealProducts) {
            this.energy += product.getEnergy();
            this.protein += product.getProtein();
            this.fat += product.getFat();
            this.carbohydrate += product.getCarbohydrate();
            this.weight += product.getWeight();
        }
        Log.d(TAG,"Meal name = " + mealName);
        Log.d(TAG,"Meal products = " + mealProducts);
    }

    public void addMealProduct(MealProduct product) {
        this.mealProducts.add(product);
        this.update();
    }
}

