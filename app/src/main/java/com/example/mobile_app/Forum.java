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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.mobile_app.Adaptors.ForumTopicAdaptor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Forum extends AppCompatActivity implements ForumTopicAdaptor.ForumTopicHolder.OnTopicClickListener {
    //Initialize drawer
    DrawerLayout drawer;

    Button goTo;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ArrayList<ForumTopic> forumTopics = new ArrayList<>();
    RecyclerView rv_forumTopic;
    ForumTopicAdaptor topicAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        drawer = findViewById(R.id.drawer_layout);

        goTo = findViewById(R.id.btn_forum_create);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("ForumTopic");

        rv_forumTopic = findViewById(R.id.rv_forum_topics);
        rv_forumTopic.setLayoutManager(new LinearLayoutManager(Forum.this));

        databaseReference.addListenerForSingleValueEvent(listener);

        ArrayList<ForumTopic> topicList;


        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Forum.this, Create_Topic.class);
                startActivity(i);
            }
        });

    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                forumTopics.add(dss.getValue(ForumTopic.class));
            }
            topicAdaptor = new ForumTopicAdaptor(forumTopics, Forum.this);
            rv_forumTopic.setAdapter(topicAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void OnTopicClick(int position) {
        Intent i = new Intent(Forum.this, topic_page.class);

        i.putExtra("ForumTopic", forumTopics.get(position));
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
        recreate();
    }

    public void ClickActivities(View view) {
        Dashboard.redirectActivity(this, Recycler_view_activities.class);
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
                Dashboard.redirectActivity(Forum.this, WelcomePage.class);
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

    @Override
    protected void onPause() {
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }
}