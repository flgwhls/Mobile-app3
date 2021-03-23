package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//class used just to add the activities in the database (this class has been used just in the background project)
public class AddActivities extends AppCompatActivity {


    DatabaseReference dbref;// reference to database

    //variables
    EditText startweek, date, hour, type, description, url;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activities);

        //views of variables
        startweek = findViewById(R.id.add_activities_startweek);
        date = findViewById(R.id.add_activities_date);
        hour = findViewById(R.id.add_activities_hour);
        type = findViewById(R.id.add_activities_type);
        description = findViewById(R.id.add_activities_descript);
        url = findViewById(R.id.add_activities_url);


        submit = findViewById(R.id.add_activities_submit);

        dbref = FirebaseDatabase.getInstance().getReference("Activities");

        //configuration of submit button that when clicked will make a new activity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activities a = new Activities(startweek.getText().toString(), date.getText().toString(), hour.getText().toString(), type.getText().toString(),
                        description.getText().toString(), url.getText().toString());

                //save object to FB
                dbref.child(dbref.push().getKey()).setValue(a);

                //intent to reset the clear view of the same page
                Intent i = new Intent(AddActivities.this, AddActivities.class);
                startActivity(i);
            }
        });
    }
}