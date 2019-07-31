package com.movie.film.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.movie.film.fragment.MovieBookFragment;
import com.movie.film.fragment.MovieUserActivitiesFragment;
import com.movie.film.fragment.MovieonRequiredDataLoaded;

public class MovieMainActivity extends MovieBaseActivity {
    private static final String CLAZZZ = MovieMainActivity.class.getName();
    private BottomNavigationView tabLayout;
    private ViewPager viewPager;
    private MovieonRequiredDataLoaded supportedProductFragment;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private Button button;
    private String userEmail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity_main);
        viewPager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbar);
        button = findViewById(R.id.get_started);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Log.e("BEFORE Action Bar", "firstLog");
        if (mDrawerToggle != null) {
            mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view == null) {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    } else {
                        mDrawerLayout.closeDrawer(GravityCompat.END);

                    }
                }
            });

        }
        Log.e("After Action Bar", "AfterrLog");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                // getActionBar().setTitle("");
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                mDrawerToggle.getToolbarNavigationClickListener();
                mDrawerLayout.addDrawerListener(mDrawerToggle);
                mDrawerLayout.closeDrawer(GravityCompat.END);

            }

            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle("MIDAS");

                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();      // creates call to onPrepareOptionsMenu()
                mDrawerToggle.getToolbarNavigationClickListener();
                mDrawerLayout.addDrawerListener(mDrawerToggle);
                mDrawerLayout.openDrawer(GravityCompat.START);

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case android.R.id.home:
                        mDrawerLayout.openDrawer(GravityCompat.START);
                        return true;
                    case R.id.menu_service:
                        showExpenseReport();
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_activity:
                        showUserActivities();
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_account:
                        straightLogout();
                        mDrawerLayout.closeDrawers();
                        return true;
                }
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
            //Log.e("Email", userEmail);
        }
    }


    public String getLoggedInUser() {
        String user = "";
        if (userEmail != null) {
            user = userEmail;
        }
        return user;
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.d(CLAZZZ, "onPause of Main  Activity is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(CLAZZZ, "onResume of Main  Activity is called");
        Intent intent = getIntent();
        if (intent != null) {

        }
    }
    private void showExpenseReport() {
        MovieBookFragment frag = new MovieBookFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }


    private void showUserActivities() {
        MovieUserActivitiesFragment frag = new MovieUserActivitiesFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onTaskCompleted(Object data) {
        super.onTaskCompleted(data);
    }




}
