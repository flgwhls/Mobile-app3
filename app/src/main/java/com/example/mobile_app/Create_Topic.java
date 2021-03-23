package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_Topic extends AppCompatActivity {

    EditText topicName, topicMessage, topicDesc;
    Button createTopic;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__topic);

        topicName = findViewById(R.id.et_ct_topic_name);
        topicMessage = findViewById(R.id.et_ct_topic_message);
        topicDesc = findViewById(R.id.et_ct_topic_desc);

        createTopic = findViewById(R.id.btn_ct_create);

        databaseReference = FirebaseDatabase.getInstance().getReference("ForumTopic");

        createTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForumTopic topic = new ForumTopic(topicName.getText().toString(), topicMessage.getText().toString(), topicDesc.getText().toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(topic);
                Intent i = new Intent(Create_Topic.this, ForumRecycleView.class);
                startActivity(i);


            }
        });

    }
}