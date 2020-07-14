package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Meal implements Parcelable {
    private static final String TAG = "Meal";

    private static ArrayList<Meal> meals = new ArrayList<Meal>();
    private ArrayList<MealProduct> mealProducts = new ArrayList<MealProduct>();

    private String mealName;
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrate;
    private double weight;


    /*static {
        Meal pilaf = new Meal("Плов");
        pilaf.addMealProduct("Рис",100);
        pilaf.addMealProduct("Лук",30);
        pilaf.addMealProduct("Морковь",30);
        pilaf.addMealProduct("Масло",10);
        pilaf.addMealProduct("Чеснок",2.5);
        pilaf.addMealProduct("Майонез",10);
        pilaf.addMealProduct("Кетчуп", 10);
        Meal porridge = new Meal("Каша");
        porridge.addMealProduct("Овсянка",70);
        porridge.addMealProduct("Молоко", 25);
        porridge.addMealProduct("Джем",10);
        Meal sandwich = new Meal("Бутерброд");
        sandwich.addMealProduct("Хлеб",50);
        sandwich.addMealProduct("Колбаса",80);
    }*/

    //Parcelable implementation

    protected Meal(Parcel in) {
        mealName = in.readString();
        energy = in.readDouble();
        protein = in.readDouble();
        fat = in.readDouble();
        carbohydrate = in.readDouble();
        weight = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mealName);
        dest.writeDouble(energy);
        dest.writeDouble(protein);
        dest.writeDouble(fat);
        dest.writeDouble(carbohydrate);
        dest.writeDouble(weight);
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
            this.energy = product.getEnergy();
            this.protein = product.getProtein();
            this.fat = product.getFat();
            this.carbohydrate = product.getCarbohydrate();
            this.weight = product.getWeight();
        }
    }

    //Constructors


    public Meal(String mealName) {
        this.mealName = mealName;
    }

    public Meal(String mealName, ArrayList<MealProduct> mealProducts) {
        this.mealName = mealName;
        this.mealProducts = mealProducts;
        this.update();
    }


}
