package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity{


    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawer = findViewById(R.id.drawer_layout);



    }

    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawer);
    }

    public static void openDrawer(DrawerLayout drawer){
        //Open drawer layout
        drawer.openDrawer(GravityCompat.START);
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


    public void VisitLibrary(View view){
        redirectActivity(this,Library.class);
    }

    public void VisitTimeTables(View view){
        redirectActivity(this,Timetables.class);
    }

    public void VisitFloorPlan(View view){
        redirectActivity(this,FloorMap.class);
    }

    public void VisitForum(View view){
        redirectActivity(this,Forum.class);
    }

    public void ClickActivities(View view){
        redirectActivity(this, Recycler_view_activities.class);
    }

    public void ClickHome(View view){
        recreate();
    }



    public static void redirectActivity(Activity activity, Class aClass){
        //Intent
        Intent i = new Intent(activity, aClass);
        // set flag
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // start activity
        activity.startActivity(i);
    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawer);
    }

}