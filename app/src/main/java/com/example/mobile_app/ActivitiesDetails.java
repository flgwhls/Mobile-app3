package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class ActivitiesDetails extends AppCompatActivity {



    ImageView iv;
    TextView startweek,date,hour,type,description;

    String url;

    //Initialize drawer
   DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_details);
        iv= findViewById(R.id.iv_act_img);
        date= findViewById(R.id.tv_activitiescard_date);
        type=findViewById(R.id.tv_act_type);
        description=findViewById(R.id.tv_act_description);
        hour=findViewById(R.id.tv_act_hour);

        drawer= findViewById(R.id.drawer_layout);


       Activities a= getIntent().getParcelableExtra("Activities");

      // Picasso.get().load(a.getUrl()).fit().into(iv);
        startweek.setText(a.getStartweek());
        date.setText(a.getDate());
        hour.setText(a.getHour());
        type.setText(a.getType());
        description.setText(a.getDescription());
        url = (a.getUrl());
        Glide.with(getApplicationContext()).load(url).into(iv);



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