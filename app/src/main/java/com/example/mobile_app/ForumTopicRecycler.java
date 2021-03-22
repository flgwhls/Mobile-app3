package com.example.mobile_app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobile_app.Adaptors.ForumTopicAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ForumTopicRecycler extends AppCompatActivity implements ForumTopicAdaptor.ForumTopicHolder.OnTopicClickListener{
    DatabaseReference databaseReference;
    ArrayList<ForumTopic> forumTopics = new ArrayList<>();
    RecyclerView rv_forumTopic;
    ForumTopicAdaptor topicAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        databaseReference = FirebaseDatabase.getInstance().getReference("ForumTopic");

        rv_forumTopic = findViewById(R.id.rv_forum_topics);
        rv_forumTopic.setLayoutManager(new LinearLayoutManager(ForumTopicRecycler.this));

        databaseReference.addListenerForSingleValueEvent(listener);
    }


    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss: snapshot.getChildren()){
                forumTopics.add(dss.getValue(ForumTopic.class));



            }
            topicAdaptor= new ForumTopicAdaptor(forumTopics,ForumTopicRecycler.this);
            rv_forumTopic.setAdapter(topicAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void OnTopicClick(int position) {
        Intent i = new Intent(ForumTopicRecycler.this, topic_page.class);

        i.putExtra("Forum Topic", forumTopics.get(position));
        startActivity(i);
    }

}
