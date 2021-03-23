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

public class AddActivities extends AppCompatActivity {

    DatabaseReference dbref;
    EditText startweek, date, hour, type, description, url;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activities);
        startweek = findViewById(R.id.add_activities_startweek);
        date = findViewById(R.id.add_activities_date);
        hour = findViewById(R.id.add_activities_hour);
        type = findViewById(R.id.add_activities_type);
        description = findViewById(R.id.add_activities_descript);
        url = findViewById(R.id.add_activities_url);


        submit = findViewById(R.id.add_activities_submit);

        dbref = FirebaseDatabase.getInstance().getReference("Activities");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Activities a = new Activities(startweek.getText().toString(), date.getText().toString(), hour.getText().toString(), type.getText().toString(),
                        description.getText().toString(), url.getText().toString());

                //3. save object to FB
                dbref.child(dbref.push().getKey()).setValue(a);

                Intent i = new Intent(AddActivities.this, AddActivities.class);
                startActivity(i);
               /* if (submit.hasOnClickListeners()) {
                    Toast.makeText(AddActivities.this, "The activity has been register", Toast.LENGTH_LONG).show();

                }*/
            }
        });
    }
}