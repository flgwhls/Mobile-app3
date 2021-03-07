package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class BookRegister extends AppCompatActivity {

    //Initialize drawer
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_map);
        drawer= findViewById(R.id.drawer_layout);
    }


    public void ClickMenu(View view){
        //Open drawer
        Dashboard.openDrawer(drawer);
    }

    public void ClickLogo(View view){
        // close drawer
        Dashboard.closeDrawer(drawer);
    }

    public void ClickHome(View view){ Dashboard.redirectActivity(this, Dashboard.class); }

    public void ClickLibrary(View view){
        Dashboard.redirectActivity(this, Library.class);
    }

    public void ClickTimetables(View view){
        Dashboard.redirectActivity(this,Timetables.class);
    }

    public void ClickFloorMap(View view){
        recreate();
    }

    public void ClickForum(View view){
        Dashboard.redirectActivity(this,Forum.class);
    }

    public void ClickActivities(View view){
        Dashboard.redirectActivity(this,Activities.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }
}