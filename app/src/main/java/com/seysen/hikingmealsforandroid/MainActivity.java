package com.seysen.hikingmealsforandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.seysen.hikingmealsforandroid.fragments.HikesFragment;
import com.seysen.hikingmealsforandroid.fragments.MealsFragment;
import com.seysen.hikingmealsforandroid.fragments.ProductsFragment;

import java.util.ArrayList;
import java.util.List;

//import static com.seysen.hikingmealsforandroid.fragments.ProductsFragment.REQUEST_CREATE_TYPE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main_activity";
    //static final int REQUEST_CREATE_TYPE=1;
    //static final int REQUEST_EDIT_TYPE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        final FloatingActionButton fab = findViewById(R.id.fab);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0: {
                        Log.d(TAG, "onPageSelected1");
                        fab.hide();
                        break;
                    }
                    case 1: {
                        Log.d(TAG, "onPageSelected2");
                        fab.hide();
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "onFloatingActionButtonClick");
                                int position = tabLayout.getSelectedTabPosition();
                                Intent intent = null;
                                switch (position) {
                                    case 0: {
                                        break;
                                    }
                                    case 1: {
                                        intent = new Intent (v.getContext(), MealDetailActivity.class);
                                        break;
                                    }
                                    case 2: {

                                        break;
                                    }
                                }
                                if (intent != null) {
                                    startActivityForResult(intent, MealsFragment.REQUEST_CREATE_TYPE);
                                }
                            }
                        });
                        fab.setImageResource(android.R.drawable.ic_menu_add);
                        fab.show();
                        break;
                    }
                    case 2: {
                        Log.d(TAG, "onPageSelected3");
                        fab.hide();
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "onFloatingActionButtonClick");
                                int position = tabLayout.getSelectedTabPosition();
                                Intent intent = null;
                                switch (position) {
                                    case 0: {
                                        break;
                                    }
                                    case 1: {
                                        break;
                                    }
                                    case 2: {
                                        intent = new Intent (v.getContext(), ProductDetailActivity.class);
                                        break;
                                    }
                                }
                                if (intent != null) {
                                    startActivityForResult(intent, ProductsFragment.REQUEST_CREATE_TYPE);
                                }
                            }
                        });
                        fab.setImageResource(android.R.drawable.ic_menu_add);
                        fab.show();
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new HikesFragment(), "Hikes");
        adapter.addFragment(new MealsFragment(), "Meals");
        adapter.addFragment(new ProductsFragment(), "Products");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
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
    }
}
