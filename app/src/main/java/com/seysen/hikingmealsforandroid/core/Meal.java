package com.seysen.hikingmealsforandroid.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.seysen.hikingmealsforandroid.fragments.MealsFragment;

import java.util.ArrayList;
import java.util.Iterator;

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

    static {
        Meal porridge = new Meal("porridge");
        porridge.addMealProduct(new MealProduct(Product.getProduct("cereals"),70));
        porridge.addMealProduct(new MealProduct(Product.getProduct("milk"), 25));
        porridge.addMealProduct(new MealProduct(Product.getProduct("dried fruits"), 25));
        porridge.addMealProduct(new MealProduct(Product.getProduct("jam"),10));
        Meal pasta = new Meal("pasta with cheese");
        pasta.addMealProduct(new MealProduct(Product.getProduct("pasta"),120));
        pasta.addMealProduct(new MealProduct(Product.getProduct("cheese"),20));
        pasta.addMealProduct(new MealProduct(Product.getProduct("mayonnaise"),10));
        pasta.addMealProduct(new MealProduct(Product.getProduct("ketchup"), 10));
        pasta.addMealProduct(new MealProduct(Product.getProduct("oil"), 5));
        Meal sandwich = new Meal("sandwich with sausage");
        sandwich.addMealProduct(new MealProduct(Product.getProduct("crispbread"),50));
        sandwich.addMealProduct(new MealProduct(Product.getProduct("sausage"),60));
        Meal cheeseSandwich = new Meal("sandwich with cheese");
        cheeseSandwich.addMealProduct(new MealProduct(Product.getProduct("crispbread"),50));
        cheeseSandwich.addMealProduct(new MealProduct(Product.getProduct("cheese"),80));
        Meal pateSandwich = new Meal("sandwich with pate");
        pateSandwich.addMealProduct(new MealProduct(Product.getProduct("crispbread"),50));
        pateSandwich.addMealProduct(new MealProduct(Product.getProduct("pate"),80));
        Meal pilaf = new Meal("pilaf");
        pilaf.addMealProduct(new MealProduct(Product.getProduct("rice"),100));
        pilaf.addMealProduct(new MealProduct(Product.getProduct("chicken"),85));
        pilaf.addMealProduct(new MealProduct(Product.getProduct("onion"),30));
        pilaf.addMealProduct(new MealProduct(Product.getProduct("carrot"),30));
        pilaf.addMealProduct(new MealProduct(Product.getProduct("oil"),10));
        pilaf.addMealProduct(new MealProduct(Product.getProduct("garlic"),2.5));
        pilaf.addMealProduct(new MealProduct(Product.getProduct("mayonnaise"),10));
        pilaf.addMealProduct(new MealProduct(Product.getProduct("ketchup"), 10));
        Meal buckwheat = new Meal("buckwheat with beef");
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("buckwheat"),90));
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("beef"),80));
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("onion"),30));
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("carrot"),30));
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("garlic"),1));
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("oil"),10));
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("mayonnaise"),10));
        buckwheat.addMealProduct(new MealProduct(Product.getProduct("ketchup"),10));
        Meal potatoes = new Meal("mashed potatoes with meat");
        potatoes.addMealProduct(new MealProduct(Product.getProduct("potato flakes"),80));
        potatoes.addMealProduct(new MealProduct(Product.getProduct("beef"),85));
        potatoes.addMealProduct(new MealProduct(Product.getProduct("onion"),30));
        potatoes.addMealProduct(new MealProduct(Product.getProduct("carrot"),30));
        potatoes.addMealProduct(new MealProduct(Product.getProduct("mayonnaise"),10));
        potatoes.addMealProduct(new MealProduct(Product.getProduct("ketchup"),10));
        potatoes.addMealProduct(new MealProduct(Product.getProduct("oil"),10));
        Meal lentils = new Meal("lentils with meat");
        lentils.addMealProduct(new MealProduct(Product.getProduct("lentils"),100));
        lentils.addMealProduct(new MealProduct(Product.getProduct("beef"),80));
        lentils.addMealProduct(new MealProduct(Product.getProduct("onion"),30));
        lentils.addMealProduct(new MealProduct(Product.getProduct("carrot"),30));
        lentils.addMealProduct(new MealProduct(Product.getProduct("mayonnaise"),10));
        lentils.addMealProduct(new MealProduct(Product.getProduct("ketchup"),10));
        lentils.addMealProduct(new MealProduct(Product.getProduct("oil"),10));
        Meal meatPasta = new Meal("pasta with beef");
        meatPasta.addMealProduct(new MealProduct(Product.getProduct("pasta"),120));
        meatPasta.addMealProduct(new MealProduct(Product.getProduct("beef"),80));
        meatPasta.addMealProduct(new MealProduct(Product.getProduct("mayonnaise"),10));
        meatPasta.addMealProduct(new MealProduct(Product.getProduct("ketchup"), 10));
        meatPasta.addMealProduct(new MealProduct(Product.getProduct("oil"), 5));
        Meal tea = new Meal("tea with cookie");
        tea.addMealProduct(new MealProduct(Product.getProduct("tea"),2.5));
        tea.addMealProduct(new MealProduct(Product.getProduct("sugar"),5));
        tea.addMealProduct(new MealProduct(Product.getProduct("condensed milk"),12));
        tea.addMealProduct(new MealProduct(Product.getProduct("cookie"),50));
    }

    //Constructors

    public Meal(String mealName, ArrayList<MealProduct> mealProducts) {
        this.mealName = mealName;
        this.mealProducts = mealProducts;
        this.update();
        meals.add(this);
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

    public static Meal getMeal(String mealName) {
        Meal result = null;
        for (Meal meal: meals) {
            if (meal.getMealName().equals(mealName)){
                result=meal;
            }
        }
        return result;
    }

    public static void removeProduct(Product product) {
        for (Meal meal: meals
             ) {
            Iterator<MealProduct> iterator = meal.getMealProducts().iterator();
            while (iterator.hasNext()) {
                MealProduct mealProduct = iterator.next();
                if (mealProduct.getProduct().equals(product)) {
                    iterator.remove();
                    meal.update();
                    MealsFragment.updateList(meal);
                }
            }
        }
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

    public void update() {
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

    public void updateMealProducts() {
        for (MealProduct mealProduct: mealProducts) {
            Product product = Product.getProduct(mealProduct.getProductID());
            mealProduct.setProduct(product);
        }
        update();
    }

    public void addMealProduct(MealProduct product) {
        if (product.getProduct()!=null) {
            this.mealProducts.add(product);
            this.update();
        }
    }
}

