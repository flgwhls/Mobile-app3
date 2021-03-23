package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import com.example.mobile_app.Adaptors.ActivitiesAdaptor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivitiesRecycler extends AppCompatActivity implements ActivitiesAdaptor.ActivitiesHolder.OnActivitiesClickListener {

    DatabaseReference dbref;
    DrawerLayout drawer;
    RecyclerView recview;
    ActivitiesAdaptor adaptor;
    ArrayList<Activities> list = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    // Modification to try date compare

    Date date;
    // end of modification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_activities);
        drawer = findViewById(R.id.drawer_layout);

        firebaseAuth = FirebaseAuth.getInstance();

        recview = findViewById(R.id.recview);
        //vertical way
        recview.setLayoutManager(new LinearLayoutManager(ActivitiesRecycler.this));
        dbref = FirebaseDatabase.getInstance().getReference("Activities");

        dbref.addListenerForSingleValueEvent(listener);

    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for (DataSnapshot dss : snapshot.getChildren()) {


                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(dss.getValue(Activities.class).getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (isInCurrentWeek(date)) {
                    list.add(dss.getValue(Activities.class));
                    //Toast.makeText(this, "curdate before date", Toast.LENGTH_SHORT).show();
                }


            }


            adaptor = new ActivitiesAdaptor(list, ActivitiesRecycler.this);
            recview.setAdapter(adaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    // Method to check if date is in the same week as date
    public static boolean isInCurrentWeek(Date date) {

        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);//Date from database activity
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    @Override
    public void OnActivitiesClick(int pos) {

        Intent i = new Intent(ActivitiesRecycler.this, ActivitiesDetails.class);
        i.putExtra("Activities", list.get(pos));
        startActivity(i);

    }

    public void ClickMenu(View view) {
        //Open drawer
        Dashboard.openDrawer(drawer);
    }

    public void ClickHome(View view) {
        Dashboard.redirectActivity(this, Dashboard.class);
    }

    public void ClickLibrary(View view) {
        Dashboard.redirectActivity(this, Library.class);
    }

    public void ClickTimetables(View view) {
        Dashboard.redirectActivity(this, Timetables.class);
    }

    public void ClickFloorMap(View view) {
        Dashboard.redirectActivity(this, FloorMap.class);
    }

    public void ClickForum(View view) {
        Dashboard.redirectActivity(this, ForumRecycler.class);
    }


    public void ClickActivities(View view) {
        recreate();
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
                //redirect activity to welcome page
                Dashboard.redirectActivity(ActivitiesRecycler.this, WelcomePage.class);
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

}
