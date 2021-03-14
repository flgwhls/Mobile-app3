package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.net.URI;


public class ActivitiesDetails extends AppCompatActivity {



    ImageView iv;
    TextView date,type;


    //Initialize drawer
   DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_details);
        iv= findViewById(R.id.iv_activities_details);
        date= findViewById(R.id.tv_activities_Date);
        type=findViewById(R.id.tv_activities_type);

        drawer= findViewById(R.id.drawer_layout);


       Activities a= getIntent().getParcelableExtra("Activities");

       Picasso.get().load(a.getUrl()).fit().into(iv);
        date.setText(a.getDate());
        type.setText(a.getType());



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
        Dashboard.redirectActivity(this, Library.class);
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

    public void ClickActivities(View view){Dashboard.redirectActivity(this,Recycler_view_activities.class);}


    public void ClickActivitiesDetails(View view){
        recreate();
    }


    @Override
    protected void onPause(){
        super.onPause();
        Dashboard.closeDrawer(drawer);

    }

}