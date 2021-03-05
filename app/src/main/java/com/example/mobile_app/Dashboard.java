package com.example.mobile_app;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void clickHome(View view){
        redirectActivity(this,Dashboard.class);
    }

    public void clickLibrary(View view){
        redirectActivity(this,Library.class);
    }

    public void clickTimetables(View view){
        redirectActivity(this,Timetables.class);
    }

    public void clickFloorMap(View view){
        redirectActivity(this,FloorMap.class);
    }

    public void clickForum(View view){
        redirectActivity(this,Forum.class);
    }

    public void clickActivities(View view){
        redirectActivity(this,Activities.class);
    }

    private static void redirectActivity(Activity activity, Class aClass){
        //Intent
        Intent i = new Intent(activity, aClass);
        // set flag
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // start activity
        activity.startActivity(i);

    }


}