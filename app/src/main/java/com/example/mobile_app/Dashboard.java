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

    public void ClickHome(View view){
        recreate();
    }

    public void ClickLibrary(View view){
        redirectActivity(this,Library.class);
    }

    public void ClickTimetables(View view){
        redirectActivity(this,Timetables.class);
    }

    public void ClickFloorMap(View view){
        redirectActivity(this,FloorMap.class);
    }

    public void ClickForum(View view){
        redirectActivity(this,Forum.class);
    }

    public void ClickActivities(View view){
        redirectActivity(this,Activities.class);
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