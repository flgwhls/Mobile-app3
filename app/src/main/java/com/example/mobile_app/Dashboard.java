package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Dashboard extends AppCompatActivity {


    //initialise firebase auth
    FirebaseAuth firebaseAuth;
    //initialise drawer
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //get drawer layout
        drawer = findViewById(R.id.drawer_layout);

        //get firebase instance
        firebaseAuth = FirebaseAuth.getInstance();


    }

    public void ClickMenu(View view) {
        //Open drawer
        openDrawer(drawer);
    }

    //close drawer
    public void ClickLogo(View view) {
        closeDrawer(drawer);
    }


    //method for opening drawer
    public static void openDrawer(DrawerLayout drawer) {
        //Open drawer layout
        drawer.openDrawer(GravityCompat.START);
    }

    //method for closing drawer
    public static void closeDrawer(DrawerLayout drawer) {
        //close drawer layout
        //check condition
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //if drawer is open
            //close it
            drawer.closeDrawer(GravityCompat.START);
        }
    }


    public void VisitLibrary(View view) {
        redirectActivity(this, Library.class);
    }

    public void VisitTimeTables(View view) {
        redirectActivity(this, Timetables.class);
    }

    public void VisitFloorPlan(View view) {
        redirectActivity(this, FloorMap.class);
    }

    public void VisitForum(View view) {
        redirectActivity(this, Forum.class);
    }

    public void VisitActivities(View view) {
        redirectActivity(this, Recycler_view_activities.class);
    }

    public void ClickHome(View view) {
        recreate();
    }


    public void ClickLibrary(View view) {
        redirectActivity(this, Library.class);
    }

    public void ClickTimetables(View view) {
        redirectActivity(this, Timetables.class);
    }

    public void ClickFloorMap(View view) {
        redirectActivity(this, FloorMap.class);
    }

    public void ClickForum(View view) {
        redirectActivity(this, Forum.class);
    }

    public void ClickActivities(View view) {
        redirectActivity(this, Recycler_view_activities.class);
    }


    //logout method
    public void ClickLogout(View view) {
        //Initialise alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("Are you sure you want to log out?");
        //Positive answer button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Logout user
                firebaseAuth.signOut();
                //redirest activity to welcome page
                redirectActivity(Dashboard.this, WelcomePage.class);
            }
        });
        //Negative answer button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }


    //method for redirecting activities
    public static void redirectActivity(Activity activity, Class aClass) {
        //Intent
        Intent i = new Intent(activity, aClass);
        // set flag
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // start activity
        activity.startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawer);
    }

}