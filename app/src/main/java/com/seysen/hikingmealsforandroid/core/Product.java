package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class Product implements Parcelable {
    private static final String TAG = "Product";
    private static ArrayList<Product> products = new ArrayList<>();

    private int productID;
    private String productName;//name of the product
    private double energy;//energy per 100 grams
    private double protein;//protein per 100 grams
    private double fat;//fat per 100 grams
    private double carbohydrate;//carbohydrate per 100 grams

    static {
        Product pasta = new Product("pasta",350,13,1.5,72);
        Product rice = new Product("rice",374,7.51,1.03,80.89);
        Product buckwheat = new Product("buckwheat",340,10.9,2.7,71.3);
        Product lentils = new Product("lentils",352,24.63,1.06,63.35);
        Product cereals = new Product("cereals",414,3.45,3.45,89.66);
        Product ketchup = new Product("ketchup",93,1.8,1,22.2);
        Product mayonnaise = new Product("mayonnaise",624,3.1,67,2.6);
        Product jam = new Product("jam",226,0,0,56.5);
        Product beef = new Product("beef",232,16.8,18.4,0);
        Product milk = new Product("milk",362,33.2,1,52.6);
        Product onion = new Product("onion",47,1.4,0,10.4);
        Product carrot = new Product("carrot",32,1.3,0.1,6.9);
        Product garlic = new Product("garlic",134,6.5,0.5,29.9);
        Product oil = new Product("oil",899,0,99,0);
        Product cheese = new Product("cheese",330,24,26,0);
        Product chicken = new Product("chicken",110,23.1,1.2,0);
        Product candy = new Product("candy", 377,0,0.1,97.5);
        Product nut = new Product("nut", 653,13,62.6,9.3);
        Product driedFruits = new Product("driedFruits",215,5.2,0.3,51);
        Product bread = new Product("bread",264,7.5,2.9,50.9);
        Product sausage = new Product("sausage",461,24,40.5,0);
    }
    //Constructor

    public Product(String productName, double energy, double protein, double fat, double carbohydrate) {
        setProductID();
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
        productID = in.readInt();
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

    public static Product getProduct(String name) {
        Product result = null;
        for (Product product: products) {
            if (product.getProductName().equals(name)) {
                result = product;
            }
        }
        return result;
    }

    public int getProductID() {
        return productID;
    }

    private void setProductID() {
        int maxID=0;
        for (Product product: products
             ) {
            if (product.productID > maxID) maxID = product.productID;
        }
        this.productID = maxID + 1;
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
        assert obj != null;
        if (obj.getClass()!=Product.class) {
            return false;
        } else {
            Product product = (Product) obj;
            return this.productID == product.getProductID();
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
        dest.writeInt(productID);
        dest.writeString(productName);
        dest.writeDouble(energy);
        dest.writeDouble(protein);
        dest.writeDouble(fat);
        dest.writeDouble(carbohydrate);
    }
}
