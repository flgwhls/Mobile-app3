package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class topic_page extends AppCompatActivity {

    TextView topicName, topicDesc, topicMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_page);


        topicName = findViewById(R.id.tv_tp_name);
        topicDesc = findViewById(R.id.tv_tp_desc);
        topicMessage = findViewById(R.id.tv_tp_message);


        ForumTopic topic = getIntent().getParcelableExtra("ForumTopic");
        topicName.setText(topic.getTopicName());
        topicDesc.setText(topic.getTopicDesc());
        topicMessage.setText(topic.getTopicMessage());
    }
}