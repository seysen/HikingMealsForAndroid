package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;

public class MealProduct extends Product{

    private String productName;//name of the product
    private double energy;//energy per weight
    private double protein;//protein per weight
    private double fat;//fat per weight
    private double carbohydrate;//carbohydrate per weight
    private double weight;

    public MealProduct(Product product, double weight) {
        this.productName = product.getProductName();
        this.energy = product.getEnergy() * weight/100;
        this.protein = product.getProtein() * weight/100;
        this.fat = product.getFat() * weight/100;
        this.carbohydrate = product.getCarbohydrate() * weight/100;
        this.weight = weight;
    }

        /*public MealProduct() {
            this("Product",0.0,0.0,0.0,0.0,0.0);
        }*/


    protected MealProduct(Parcel in) {
        productName = in.readString();
        energy = in.readDouble();
        protein = in.readDouble();
        fat = in.readDouble();
        carbohydrate = in.readDouble();
        weight = in.readDouble();
    }

    public static final Creator<MealProduct> CREATOR = new Creator<MealProduct>() {
        @Override
        public MealProduct createFromParcel(Parcel in) {
            return new MealProduct(in);
        }

        @Override
        public MealProduct[] newArray(int size) {
            return new MealProduct[size];
        }
    };

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public double getEnergy() {
        return super.getEnergy();
    }

    @Override
    public double getProtein() {
        return super.getProtein();
    }

    @Override
    public double getFat() {
        return super.getFat();
    }

    @Override
    public double getCarbohydrate() {
        return super.getCarbohydrate();
    }

    @Override
    public void setEnergy(double energy) {
        super.setEnergy(energy);
    }

    @Override
    public void setProtein(double protein) {
        super.setProtein(protein);
    }

    @Override
    public void setFat(double fat) {
        super.setFat(fat);
    }

    @Override
    public void setCarbohydrate(double carbohydrate) {
        super.setCarbohydrate(carbohydrate);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeDouble(energy);
        dest.writeDouble(protein);
        dest.writeDouble(fat);
        dest.writeDouble(carbohydrate);
        dest.writeDouble(weight);
    }
}
