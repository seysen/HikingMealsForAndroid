package com.seysen.hikingmealsforandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.seysen.hikingmealsforandroid.core.Meal;
import com.seysen.hikingmealsforandroid.core.MealProduct;
import com.seysen.hikingmealsforandroid.core.Product;
import com.seysen.hikingmealsforandroid.fragments.HikesFragment;
import com.seysen.hikingmealsforandroid.fragments.MealsFragment;
import com.seysen.hikingmealsforandroid.fragments.ProductsFragment;

import java.util.ArrayList;
import java.util.List;

import static com.seysen.hikingmealsforandroid.fragments.MealsFragment.MEALNAME;
import static com.seysen.hikingmealsforandroid.fragments.ProductsFragment.PRODUCTNAME;
import static com.seysen.hikingmealsforandroid.fragments.ProductsFragment.REQUEST_CREATE_TYPE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main_activity";
    private Adapter adapter = null;
    private ViewPager viewPager = null;
    private TabLayout tabLayout = null;
    private static Fragment activeFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }


        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        final FloatingActionButton fab = findViewById(R.id.fab);
        assert viewPager != null;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                activeFragment = adapter.getRegisteredFragment(i);
                /*switch (i) {
                    case 0: {
                        Log.d(TAG, "onPageSelected1");
                        fab.hide();
                        fab.setImageResource(android.R.drawable.ic_menu_add);
                        fab.show();
                        break;
                    }
                    case 1: {
                        Log.d(TAG, "onPageSelected2");
                        fab.hide();
                        fab.setImageResource(android.R.drawable.ic_menu_add);
                        fab.show();
                        break;
                    }
                    case 2: {
                        Log.d(TAG, "onPageSelected3");
                        fab.hide();
                        fab.setImageResource(android.R.drawable.ic_menu_add);
                        fab.show();
                        break;
                    }
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onFloatingActionButtonClick");

                Log.d(TAG, "view" + v.getContext());
                Intent intent = null;
                switch (tabLayout.getSelectedTabPosition()) {
                    case 0: {
                        intent = new Intent(v.getContext(), HikeDetailActivity.class);
                        break;
                    }
                    case 1: {
                        intent = new Intent(v.getContext(), MealDetailActivity.class);
                        break;
                    }
                    case 2: {
                        intent = new Intent(v.getContext(), ProductDetailActivity.class);
                        break;
                    }
                }
                startActivityForResult(intent, REQUEST_CREATE_TYPE);
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d(TAG, "onActivityResult");
        switch (tabLayout.getSelectedTabPosition()) {
            case 0: {
                if (requestCode == REQUEST_CREATE_TYPE) {
                    Log.d(TAG, "Request create");
                    if (resultCode == 0) {
                        Log.d(TAG, "Result OK");
                        HikesFragment hikesFragment = (HikesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_hikes);
                        assert hikesFragment != null;
                        HikesFragment.addHike();
                    } else {
                        Log.d(TAG, "Create canceled");
                    }
                    break;
                }
                break;
            }
            case 1: {
                if (requestCode == REQUEST_CREATE_TYPE) {
                    Log.d(TAG, "Request create");
                    if (resultCode == 0) {
                        Log.d(TAG, "Result OK");
                        //Meal mMeal = data.getParcelableExtra(MEALNAME);
                        MealsFragment mealsFragment = (MealsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_meals);
                        assert mealsFragment != null;
                        MealsFragment.addMeal();
                    } else {
                        Log.d(TAG, "Create canceled");
                    }
                    break;
                }
            }
            case 2: {
                if (requestCode == REQUEST_CREATE_TYPE) {
                    Log.d(TAG, "Request create");
                    if (resultCode == 0) {
                        Log.d(TAG, "Result OK");
                        //Product mProduct = data.getParcelableExtra(PRODUCTNAME);
                        ProductsFragment productFragment = (ProductsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_products);
                        assert productFragment != null;
                        ProductsFragment.addProduct();
                    } else {
                        Log.d(TAG, "Create canceled");
                    }
                    break;
                }
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new HikesFragment(), "Hikes");
        adapter.addFragment(new MealsFragment(), "Meals");
        adapter.addFragment(new ProductsFragment(), "Products");
        viewPager.setAdapter(adapter);
    }

    public static Fragment getActiveFragment() {
        return activeFragment;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        SparseArray<Fragment> registeredFragments = new SparseArray<>();

        Adapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container,position);
            registeredFragments.put(position,fragment);
            return fragment;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }
    }
}
