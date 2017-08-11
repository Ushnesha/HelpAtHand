package com.example.android.helphand;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.tv_feedback) {
                    Class destinationClass = feedbackActivity.class;
                    Intent intentToStartUserActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartUserActivity);
                    return true;
                }
                if (id == R.id.tv_signup) {

                    Class destinationClass = RegisterActivity.class;
                    Intent intentToStartRegisterActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartRegisterActivity);
                    return true;
                }
                if (id == R.id.tv_login) {

                    Class destinationClass = LogInActivity.class;
                    Intent intentToStartLogInActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartLogInActivity);
                    return true;
                }
                if (id == R.id.tv_about) {
                    Class destinationClass = AboutActivity.class;
                    Intent intentToStartAboutActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartAboutActivity);
                    return true;
                }
//
                if (id == R.id.tv_home) {

                    Class destinationClass = MainActivity.class;
                    Intent intentToStartMainActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartMainActivity);

                    return true;
                }

                if (id == R.id.tv_activity) {

                    Class destinationClass = UserActivity.class;
                    Intent intentToStartUserActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartUserActivity);
                    return true;
                }

                if (id == R.id.tv_ride) {

                    Class destinationClass = RideActivity.class;
                    Intent intentToStartUserActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartUserActivity);
                    return true;
                }

                if (id == R.id.tv_help) {

                    Class destinationClass = HelpActivity.class;
                    Intent intentToStartUserActivity = new Intent(MainActivity.this, destinationClass);
                    startActivity(intentToStartUserActivity);
                    return true;
                }

                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        super.onOptionsItemSelected(item);
        return true;
    }
}