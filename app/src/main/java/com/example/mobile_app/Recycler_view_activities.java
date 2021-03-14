package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.mobile_app.Adaptors.ActivitiesAdaptor;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Recycler_view_activities extends AppCompatActivity {


    DrawerLayout drawer;
    RecyclerView recview;
    ActivitiesAdaptor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_activities);

        drawer=findViewById(R.id.drawer_layout);
        recview = findViewById(R.id.recview);
        //vertical way
        recview.setLayoutManager(new LinearLayoutManager(Recycler_view_activities.this));



        //test
        FirebaseRecyclerOptions<Activities> options =
                new FirebaseRecyclerOptions.Builder<Activities>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Activities"), Activities.class)
                        .build();
        adapter = new ActivitiesAdaptor(options);
        recview.setAdapter(adapter);


    }
    @Override
    protected void onStart (){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop (){
        super.onStop();
        adapter.stopListening();
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

    public void ClickLibrary(View view){Dashboard.redirectActivity(this, Library.class);}

    public void ClickTimetables(View view){
        Dashboard.redirectActivity(this,Timetables.class);
    }

    public void ClickFloorMap(View view){
        Dashboard.redirectActivity(this,FloorMap.class);
    }

    public void ClickForum(View view){
        Dashboard.redirectActivity(this,Forum.class);
    }


    public void ClickRecycler_view_activities(View view){
        recreate();
    }


}
