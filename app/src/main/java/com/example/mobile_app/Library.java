package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.mobile_app.Adaptors.BookAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Library extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    //Initialize drawer
    DrawerLayout drawer;
    //TODO: copy all recyclerView to LibRecyklerView and call LibRecyclerView when it is needed


    // reference to database
    DatabaseReference dbref;
    //Chosen category
    String menuanswer;
    ArrayList<Book> booklist = new ArrayList<>();
    RecyclerView rv_library_search;
    BookAdaptor bookAdaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        drawer= findViewById(R.id.drawer_layout);
        // DataBase Reference
        dbref = FirebaseDatabase.getInstance().getReference("Books");
        rv_library_search = findViewById(R.id.rv_library_search);
        rv_library_search.setLayoutManager( new LinearLayoutManager(Library.this));//Vertical view
        //rv_library_search.setLayoutManager( new LinearLayoutManager(myRecyclerView.this, LinearLayoutManager.HORIZONTAL,reverseLayout: true)); //Horizontal reversal
        //rv_library_search.setLayoutManager( new GridLayoutManager(myRecyclerView.this),spanCount: 3); // Vertical 3 columns

    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss: snapshot.getChildren()){
                booklist.add(dss.getValue(Book.class));
            }
            bookAdaptor = new BookAdaptor(booklist);
            rv_library_search.setAdapter(bookAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

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

    public void ClickLibrary(View view){
        recreate();
    }

    public void ClickTimetables(View view){
        Dashboard.redirectActivity(this,Timetables.class);
    }

    public void ClickFloorMap(View view){
        Dashboard.redirectActivity(this,FloorMap.class);
    }

    public void ClickForum(View view){
        Dashboard.redirectActivity(this,Forum.class);
    }

    public void ClickActivities(View view){
        Dashboard.redirectActivity(this,Activities.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }
    public void showMenu(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }
    // Method to choose books in one category and view in Recycler View

    // use to get answer and view cards in RecyclerView
    @Override
    public boolean onMenuItemClick(MenuItem item){

        switch (item.getItemId()){
            case R.id.all:
                // when all
                menuanswer = "all";
                Toast.makeText(this, "All", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cloud:
                // when cloud
                menuanswer = "cloud";
                Toast.makeText(this, "cloud", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.graphics:
                // when graphics
                menuanswer = "graphics";
                Toast.makeText(this, "graphics", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.network:
                // when network
                menuanswer = "network";
                Toast.makeText(this, "network", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.programming:
                // when programming
                menuanswer = "programming";
                Toast.makeText(this, "programming", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.web:
                // when web
                menuanswer = "web";
                Toast.makeText(this, "web", Toast.LENGTH_SHORT).show();

                return true;
            default:
                 // like all
                menuanswer = "all";
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
                return true;

        } //end switch

    }
}