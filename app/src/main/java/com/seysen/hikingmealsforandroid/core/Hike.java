package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Hike implements Parcelable {
    //variables
    public static ArrayList<Hike> hikes = new ArrayList<>();
    private String hikeName;
    private int quantity;
    private ArrayList<HikeDay> hikeDays = new ArrayList<>();
    //private HashMap<String,Double> shippingChart = new HashMap<>();
    private ArrayList<MealProduct> shoppingList = new ArrayList<>();
    //methods

    static {
        Hike pvd2 = new Hike("pvd 2 days",2,2);
        pvd2.getHikeDay(0).addMeal(Meal.getMeal("sandwich with sausage"));
        pvd2.getHikeDay(0).addMeal(Meal.getMeal("pilaf"));
        pvd2.getHikeDay(0).addMeal(Meal.getMeal("tea with cookie"));
        pvd2.getHikeDay(1).addMeal(Meal.getMeal("porridge"));
        pvd2.getHikeDay(1).addMeal(Meal.getMeal("tea with cookie"));
        pvd2.getHikeDay(1).addMeal(Meal.getMeal("sandwich with cheese"));
        Hike pvd6 = new Hike("pvd 6 days",5,6);
        pvd6.getHikeDay(0).addMeal(Meal.getMeal("sandwich with sausage"));
        pvd6.getHikeDay(0).addMeal(Meal.getMeal("pilaf"));
        pvd6.getHikeDay(0).addMeal(Meal.getMeal("tea with cookie"));
        pvd6.getHikeDay(1).addMeal(Meal.getMeal("pasta with cheese"));
        pvd6.getHikeDay(1).addMeal(Meal.getMeal("sandwich with cheese"));
        pvd6.getHikeDay(1).addMeal(Meal.getMeal("buckwheat with beef"));
        pvd6.getHikeDay(1).addMeal(Meal.getMeal("tea with cookie"));
        pvd6.getHikeDay(2).addMeal(Meal.getMeal("porridge"));
        pvd6.getHikeDay(2).addMeal(Meal.getMeal("sandwich with pate"));
        pvd6.getHikeDay(2).addMeal(Meal.getMeal("mashed potatoes with meat"));
        pvd6.getHikeDay(2).addMeal(Meal.getMeal("tea with cookie"));
        pvd6.getHikeDay(3).addMeal(Meal.getMeal("pasta with cheese"));
        pvd6.getHikeDay(3).addMeal(Meal.getMeal("sandwich with sausage"));
        pvd6.getHikeDay(3).addMeal(Meal.getMeal("lentils with meat"));
        pvd6.getHikeDay(3).addMeal(Meal.getMeal("tea with cookie"));
        pvd6.getHikeDay(4).addMeal(Meal.getMeal("porridge"));
        pvd6.getHikeDay(4).addMeal(Meal.getMeal("sandwich with cheese"));
        pvd6.getHikeDay(4).addMeal(Meal.getMeal("pasta with beef"));
        pvd6.getHikeDay(4).addMeal(Meal.getMeal("tea with cookie"));
        pvd6.getHikeDay(5).addMeal(Meal.getMeal("porridge"));
        pvd6.getHikeDay(5).addMeal(Meal.getMeal("sandwich with pate"));
        pvd6.getHikeDay(5).addMeal(Meal.getMeal("tea with cookie"));
    }

    protected Hike(Parcel in) {
        hikeName = in.readString();
        quantity = in.readInt();
        hikeDays = in.createTypedArrayList(HikeDay.CREATOR);
    }

    public static final Creator<Hike> CREATOR = new Creator<Hike>() {
        @Override
        public Hike createFromParcel(Parcel in) {
            return new Hike(in);
        }

        @Override
        public Hike[] newArray(int size) {
            return new Hike[size];
        }
    };

    public int getDuration() {
        return hikeDays.size();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDuration (int duration) {
        if (hikeDays.size()>duration) {
            for (; duration <hikeDays.size(); ) {
                hikeDays.remove(duration);
            }
        } else if (hikeDays.size()<duration) {
            for (int i = hikeDays.size(); i<duration; i++){
                hikeDays.add(new HikeDay());
            }
        }
    }

    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }

    public static ArrayList<Hike> getHikes() {
        return hikes;
    }

    public static void setHikes(ArrayList<Hike> hikes) {
        Hike.hikes = hikes;
    }

    public String getHikeName() {
        return hikeName;
    }

    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    public ArrayList<HikeDay> getHikeDays() {
        return hikeDays;
    }

    public HikeDay getHikeDay(int index) {
        return hikeDays.get(index);
    }

    public void setHikeDays(ArrayList<HikeDay> hikeDays) {
        this.hikeDays = hikeDays;
    }

    public void addDay(HikeDay hikeDay) {
        hikeDays.add(hikeDay);
    }
    public void addDay(int index, HikeDay hikeDay) {
        hikeDays.add(index,hikeDay);
    }
    public void removeDay() {
        hikeDays.remove(hikeDays.size()-1);
    }
    public void removeDay(int index) {
        hikeDays.remove(index-1);
    }
    public ArrayList<MealProduct> getShoppingList() {
        return shoppingList;
    }

    /*public void generateShoppingList() {
        HashMap<String, Double> chart = new HashMap<String, Double>();
        for (HikeDay hikeDay : hikeDays
        ) {
            for (Meal meal : hikeDay.getMeals()
            ) {
                for (MealProduct mProduct: meal.getMealProducts()
                ) {
                    Product product = mProduct.getProduct();
                    double weight = mProduct.getWeight();
                    if (chart.keySet().contains(product.getProductName())) {
                        chart.put(product.getProductName(), chart.get(product.getProductName()) + weight * this.getQuantity());
                    } else {
                        chart.put(product.getProductName(), weight * this.getQuantity());
                    }
                }
            }
        }
        shippingChart = chart;
    }*/

    public void generateShoppingList() {
        this.shoppingList.clear();
        for (HikeDay hikeDay: hikeDays
             ) {
            ArrayList<Meal> meals = hikeDay.getMeals();
            for (Meal meal:meals
                 ) {
                ArrayList<MealProduct> mealProducts = meal.getMealProducts();
                for (MealProduct mProduct: mealProducts
                     ) {
                    if (this.shoppingList.contains(mProduct)) {
                        for (MealProduct product:this.shoppingList
                        ) {
                            if (product.equals(mProduct)) {
                                double weight = product.getWeight();
                                product.setWeight(weight+mProduct.getWeight()*quantity);
                            }
                        }
                    } else {
                        this.shoppingList.add(new MealProduct(mProduct.getProduct(),mProduct.getWeight()*quantity));
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.hikeName;
    }

    //constructors

    public Hike() {
        this.hikeName = "";
        this.quantity = 1;
        hikeDays.add(new HikeDay());
        hikes.add(this);
    }

    public Hike(String hikeName) {
        this.hikeName = hikeName;
        this.quantity = 1;
        hikeDays.add(new HikeDay());
        hikes.add(this);
    }

    public Hike(String hikeName, int quantity) {
        this.hikeName = hikeName;
        this.quantity = quantity;
        hikeDays.add(new HikeDay());
        hikes.add(this);
    }

    public Hike(String hikeName, int quantity, int duration) {
        this.hikeName = hikeName;
        this.quantity = quantity;
        for (int i=0;i<duration;i++) {
            hikeDays.add(new HikeDay());
        }
        hikes.add(this);
    }

    public Hike(String hikeName, int quantity, ArrayList<HikeDay> hikeDays) {
        this.hikeName = hikeName;
        this.quantity = quantity;
        this.hikeDays = hikeDays;
        hikes.add(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hikeName);
        dest.writeInt(quantity);
        dest.writeTypedList(hikeDays);
    }

    public double getWeight() {
        double weight = 0;
        for (HikeDay day: hikeDays
             ) {
            weight += day.getDayWeight();
        }
        return weight;
    }

    public void remove() {
        hikes.remove(this);
    }
}
