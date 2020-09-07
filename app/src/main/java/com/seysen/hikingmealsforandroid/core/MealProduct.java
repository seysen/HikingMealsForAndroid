package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

public class MealProduct implements Parcelable {

    private final static String TAG = "MealProduct";
    private Product product;//name of the product
    private String productName;
    private double energy;//energy per weight
    private double protein;//protein per weight
    private double fat;//fat per weight
    private double carbohydrate;//carbohydrate per weight
    private double weight;

    public MealProduct(Product product, double weight) {
        this.product = product;
        Log.d(TAG,"Product = " + product);
        this.productName = product.getProductName();
        this.weight = weight;
        this.update();
    }

    protected MealProduct(Parcel in) {
        product = in.readParcelable(Product.class.getClassLoader());
        assert product != null;
        productName = product.getProductName();
        weight = in.readDouble();
        update();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(product,flags);
        dest.writeDouble(weight);
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

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return productName;
    }

    public double getEnergy() {
        return energy;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private void update() {
        this.energy = product.getEnergy()*weight/100;
        this.protein = product.getProtein()*weight/100;
        this.fat = product.getFat()*weight/100;
        this.carbohydrate = product.getCarbohydrate()*weight/100;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setProduct(Product product) {
        this.product = product;
        productName = product.getProductName();
        update();
    }

    public int getProductID() {
        return this.product.getProductID();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof MealProduct) {
            return this.getProduct().getProductID() == ((MealProduct) obj).getProduct().getProductID();
        } else {
            return false;
        }
    }
}
