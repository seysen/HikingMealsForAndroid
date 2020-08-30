package com.seysen.hikingmealsforandroid.helper;

import android.content.Context;

import com.google.gson.Gson;
import com.seysen.hikingmealsforandroid.core.Hike;
import com.seysen.hikingmealsforandroid.core.Meal;
import com.seysen.hikingmealsforandroid.core.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JSONHelper {
    private static final String DATA_FILE = "data.json";

    static boolean exportProductsToJSON (Context context, ArrayList<Product> products) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setProducts(products);
        String jsonString = gson.toJson(dataItems);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(DATA_FILE,Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    static boolean exportMealsToJSON (Context context, ArrayList<Meal> meals) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setMeals(meals);
        String jsonString = gson.toJson(dataItems);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(DATA_FILE,Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    static boolean exportHikesToJSON (Context context, ArrayList<Hike> hikes) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setHikes(hikes);
        String jsonString = gson.toJson(dataItems);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(DATA_FILE,Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static ArrayList<Product> importProductsFromJSON(Context context) {
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(DATA_FILE);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return dataItems.getProducts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static ArrayList<Meal> importMealsFromJSON(Context context) {
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(DATA_FILE);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return dataItems.getMeals();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static ArrayList<Hike> importHikesFromJSON(Context context) {
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(DATA_FILE);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return dataItems.getHikes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static class DataItems {
        private ArrayList<Product> products;
        private ArrayList<Meal> meals;
        private ArrayList<Hike> hikes;

        public ArrayList<Product> getProducts() {
            return products;
        }

        public void setProducts(ArrayList<Product> products) {
            this.products = products;
        }

        public ArrayList<Meal> getMeals() {
            return meals;
        }

        public void setMeals(ArrayList<Meal> meals) {
            this.meals = meals;
        }

        public ArrayList<Hike> getHikes() {
            return hikes;
        }

        public void setHikes(ArrayList<Hike> hikes) {
            this.hikes = hikes;
        }
    }
}
