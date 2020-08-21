package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

public class Hike implements Parcelable {
    //variables
    public static ArrayList<Hike> hikes = new ArrayList<Hike>();
    private String hikeName;
    private int quantity;
    private ArrayList<HikeDay> hikeDays = new ArrayList<HikeDay>();
    private HashMap<Product,Double> shippingChart = new HashMap<Product,Double>();
    //methods

    static {
        Hike pvd2 = new Hike("pvd 2 days",2,2);
        for (HikeDay hikeDay: pvd2.getHikeDays()) {
            hikeDay.addMeal(Meal.getMeal("porridge"));
            hikeDay.addMeal(Meal.getMeal("sandwich"));
            hikeDay.addMeal(Meal.getMeal("pilaf"));
        }
        Hike pvd6 = new Hike("pvd 6 days",2,6);
        for (HikeDay hikeDay: pvd6.getHikeDays()) {
            hikeDay.addMeal(Meal.getMeal("porridge"));
            hikeDay.addMeal(Meal.getMeal("sandwich"));
            hikeDay.addMeal(Meal.getMeal("pilaf"));
        }
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
            while (hikeDays.size()!=duration) {
                hikeDays.remove(duration);
            }
        } else if (hikeDays.size()<duration) {
            while (hikeDays.size()!=duration) {
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
    public HashMap<Product,Double> getShippingChart() {
        return shippingChart;
    }
    /*public void generateShippingChart() {
        HashMap<Product,Double> chart = new HashMap<Product, Double>();
        for (HikeDay hikeDay:hikeDays
        ) {
            for (Meal meal:hikeDay.getMeals()
            ) {
                for (HashMap.Entry<Product, Double> pair : meal.getMealProducts().entrySet()
                ) {
                    Product product = pair.getKey();
                    double weight = pair.getValue();
                    if (chart.keySet().contains(product)) {
                        chart.put(product,chart.get(product)+weight*Hike.this.getQuantity());
                    } else {
                        chart.put(product,weight*Hike.this.getQuantity());
                    }
                }
            }
        }
        shippingChart = chart;
    }*/
    @Override
    public String toString() {
        return this.hikeName;
    }

    //constructors

    public Hike(String hikeName) {
        this.hikeName = hikeName;
        this.quantity = 0;
        hikes.add(this);
        hikeDays.add(new HikeDay());
    }

    public Hike(String hikeName, int quantity) {
        this.hikeName = hikeName;
        this.quantity = quantity;
        hikes.add(this);
        hikeDays.add(new HikeDay());
    }

    public Hike(String hikeName, int quantity, int duration) {
        this.hikeName = hikeName;
        this.quantity = quantity;
        hikes.add(this);
        for (int i=0;i<duration;i++) {
            hikeDays.add(new HikeDay());
        }
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

    //TODO getHikeWeight
    /*public Double getWeight() {
        return weight;
    }*/

}
