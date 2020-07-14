package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Product implements Parcelable {
    private static final String TAG = "Product";
    private static ArrayList<Product> products = new ArrayList<Product>();

    private String productName;//name of the product
    private double energy;//energy per 100 grams
    private double protein;//protein per 100 grams
    private double fat;//fat per 100 grams
    private double carbohydrate;//carbohydrate per 100 grams

    private static final String PRODUCTNAME = "product_name";
    private static final String PRODUCTENERGY = "product_energy";
    private static final String PRODUCTPROTEIN = "product_protein";
    private static final String PRODUCTFAT = "product_fat";
    private static final String PRODUCTCARBOHYDRATE = "product_carbohydrate";

    //Constructor

    public Product(String productName, double energy, double protein, double fat, double carbohydrate) {
        this.productName = productName;
        this.energy = energy;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        products.add(this);
    }

    public Product() {
        this("Product",0.0,0.0,0.0,0.0);
    }


    protected Product(Parcel in) {
        productName = in.readString();
        energy = in.readDouble();
        protein = in.readDouble();
        fat = in.readDouble();
        carbohydrate = in.readDouble();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    //Methods

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public Product getProduct(String name) {
        Product result = null;
        for (Product product: products) {
            if (product.getProductName().equals(name)) {
                result = product;
            }
        }
        return result;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        try {
            Product product = (Product) obj;
            if (product.getEnergy()==this.getEnergy()&&
                    product.getProtein()==this.getProtein()&&
                    product.getFat()==this.getFat()&&
                    product.getCarbohydrate()==this.getCarbohydrate()) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return productName;
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
    }
}
