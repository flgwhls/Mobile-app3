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
    EditText startweek,date,hour,url,type,description;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activities);
        startweek=findViewById(R.id.add_activities_startweek);
        date= findViewById(R.id.add_activities_date);
        hour=findViewById(R.id.add_activities_hour);
        url= findViewById(R.id.add_activities_url);
        type=findViewById(R.id.add_activities_type);
        description=findViewById(R.id.add_activities_descript);


        submit=findViewById(R.id.add_activities_submit);

        dbref= FirebaseDatabase.getInstance().getReference("Activities");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Activities a =new Activities(startweek.getText().toString(),date.getText().toString(),hour.getText().toString(),url.getText().toString(),type.getText().toString(),
                        description.getText().toString());

                //3. save object to FB
                dbref.child(dbref.push().getKey()).setValue(a);

               /* if (submit.hasOnClickListeners()) {
                    Toast.makeText(AddActivities.this, "The activity has been register", Toast.LENGTH_LONG).show();

                }*/
            }
        });
    }
}