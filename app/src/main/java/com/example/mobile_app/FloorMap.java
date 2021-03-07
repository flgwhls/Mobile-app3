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

public class FloorMap extends AppCompatActivity {

     DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_map);

        drawer= findViewById(R.id.drawer_layout);


    }


    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawer);
    }

    public static void openDrawer(DrawerLayout drawer){
        //Open drawer layout
        drawer.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        // close drawer
        closeDrawer(drawer);
    }

    public static void closeDrawer(DrawerLayout drawer) {
        //close drawer layout
        //check condition
        if (drawer.isDrawerOpen(GravityCompat.START)){
            //if drawer is open
            //close it
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void clickMenu(View view){
        Dashboard.openDrawer(drawer);
    }

    public void clickLogo(View view){
        Dashboard.closeDrawer(drawer);
    }

    public void cLickHome(View view){
        Dashboard.redirectActivity(this, Dashboard.class);
    }

    public void clickLibrary(View view){
        Dashboard.redirectActivity(this, Library.class);
    }

    public void cLickTimeTables(View view){
        Dashboard.redirectActivity(this, Timetables.class);
    }

    public void cLickFloorMap(View view){
        recreate();
    }

    public void cLickForum(View view){
        Dashboard.redirectActivity(this, Forum.class);
    }

    public void cLickActivities(View view){
        Dashboard.redirectActivity(this, Activities.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }
}