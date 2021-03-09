package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Library extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    //Initialize drawer
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
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

    public void ClickHome(View view){
        Dashboard.redirectActivity(this, Dashboard.class);
    }

    public void ClickLibrary(View view){
        recreate();
    }

    public void ClickTimetables(View view){
        Dashboard.redirectActivity(this,Timetables.class);
    }

    public void ClickFloorMap(View view){
        Dashboard.redirectActivity(this,FloorMap.class);
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
    public void showMenu(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.all:
                // when all
                Toast.makeText(this, "All", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cloud:
                // when cloud
                Toast.makeText(this, "cloud", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.graphics:
                // when graphics
                Toast.makeText(this, "graphics", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.network:
                // when network
                Toast.makeText(this, "network", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.programing:
                // when programming
                Toast.makeText(this, "programming", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.web:
                // when web
                Toast.makeText(this, "web", Toast.LENGTH_SHORT).show();

                return true;
            default:
                 // like all
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
                return true;

        }
    }
}