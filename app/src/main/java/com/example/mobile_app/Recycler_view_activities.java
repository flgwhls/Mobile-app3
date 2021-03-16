package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mobile_app.Adaptors.ActivitiesAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Recycler_view_activities extends AppCompatActivity implements ActivitiesAdaptor.ActivitiesHolder.OnActivitiesClickListener{


    DatabaseReference dbref;
    DrawerLayout drawer;
    RecyclerView recview;
    ActivitiesAdaptor adaptor;
    ArrayList<Activities> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_activities);

        drawer=findViewById(R.id.drawer_layout);
        recview = findViewById(R.id.recview);
        //vertical way
        recview.setLayoutManager(new LinearLayoutManager(Recycler_view_activities.this));




        dbref= FirebaseDatabase.getInstance().getReference("Activities");
        dbref.addListenerForSingleValueEvent(listener);
    }
    ValueEventListener listener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for (DataSnapshot dss: snapshot.getChildren())
            {
                list.add(dss.getValue(Activities.class));

            }
            adaptor=new ActivitiesAdaptor(list, Recycler_view_activities.this);
            recview.setAdapter(adaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void OnActivitiesClick(int pos) {

        Intent i= new Intent(Recycler_view_activities.this,ActivitiesDetails.class);
        i.putExtra("Activities", list.get(pos));
        startActivity(i);

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
