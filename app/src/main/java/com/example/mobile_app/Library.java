package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

public class Library extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener, PopupMenu.OnMenuItemClickListener {

    //Initialize drawer
    DrawerLayout drawer;
    //TODO: copy all recyclerView to LibRecyklerView and call LibRecyclerView when it is needed


    // reference to database
    DatabaseReference dbref;
    //Chosen category
    String menuanswer,catread;
    ArrayList<Book> allbooklist = new ArrayList<>(); //all Books
    ArrayList<Book> booklist = new ArrayList<>(); // array to view
    ArrayList<Book> cloudlist = new ArrayList<>(); //Cloud Computing
    ArrayList<Book> dbasedlist = new ArrayList<>(); //Databases
    ArrayList<Book> weblist = new ArrayList<>(); // Web Design
    ArrayList<Book> graphlist = new ArrayList<>(); // Computer Graphics
    ArrayList<Book> netlist = new ArrayList<>(); //Network Design
    ArrayList<Book> proglist = new ArrayList<>(); // Programming
    RecyclerView rv_library;
    BookAdaptor mybookAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        drawer= findViewById(R.id.drawer_layout);
        // DataBase Reference
        dbref = FirebaseDatabase.getInstance().getReference("Book");
        rv_library = findViewById(R.id.recview);
        rv_library.setLayoutManager( new LinearLayoutManager(Library.this));//Vertical view
        //rv_library_search.setLayoutManager( new LinearLayoutManager(myRecyclerView.this, LinearLayoutManager.HORIZONTAL,reverseLayout: true)); //Horizontal reversal
        //rv_library_search.setLayoutManager( new GridLayoutManager(myRecyclerView.this),spanCount: 3); // Vertical 3 columns
        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss: snapshot.getChildren()){
                allbooklist.add(dss.getValue(Book.class)); //Put all book

                // write books in Cloud Computing category to cloudlist
                if(dss.getValue(Book.class).getCategory().equals("Cloud Computing")){
                    cloudlist.add(dss.getValue(Book.class));
                }
                // write books in Databases category to dbaselist
                if(dss.getValue(Book.class).getCategory().equals("Databases")){
                    dbasedlist.add(dss.getValue(Book.class));
                }
                // write books in Computer Graphics category to graphlist
                if(dss.getValue(Book.class).getCategory().equals("Computer Graphics")){
                    graphlist.add(dss.getValue(Book.class));
                }
                // write books in Network Design category to netlist
                if(dss.getValue(Book.class).getCategory().equals("Network Design")){
                    netlist.add(dss.getValue(Book.class));
                }// write books in Programming category to proglist
                if(dss.getValue(Book.class).getCategory().equals("Programming")){
                    proglist.add(dss.getValue(Book.class));
                }// write books in Web Design category to graphlist
                if(dss.getValue(Book.class).getCategory().equals("Web Design")){
                    weblist.add(dss.getValue(Book.class));
                }

            }
           /* menuanswer="booklist";
            mybookAdaptor = new BookAdaptor(booklist,Library.this);
            rv_library.setAdapter(mybookAdaptor);
*/
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
        Dashboard.redirectActivity(this, Recycler_view_activities.class);
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


    // use to get answer and view cards in RecyclerView
    @Override
    public boolean onMenuItemClick(MenuItem item){

        switch (item.getItemId()){
          /*  case R.id.all:
                // when all
                menuanswer = "all";
                mybookAdaptor = new BookAdaptor(booklist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;*/
            case R.id.cloud:
                // when cloud
                menuanswer = "cloudlist";
                //Toast.makeText(this, "cloud", Toast.LENGTH_SHORT).show();
                mybookAdaptor = new BookAdaptor(cloudlist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;
            case R.id.graphics:
                // when graphics
                menuanswer = "graphlist";
                mybookAdaptor = new BookAdaptor(graphlist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;
            case R.id.network:
                // when network
                menuanswer = "netlist";
                mybookAdaptor = new BookAdaptor(netlist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;
            case R.id.dbase:
                // when network
                menuanswer = "dbasedlist";
                mybookAdaptor = new BookAdaptor(dbasedlist,Library.this);
                rv_library.setAdapter(mybookAdaptor);

                return true;
            case R.id.programming:
                // when programming
                menuanswer = "proglist";
                mybookAdaptor = new BookAdaptor(proglist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;
            case R.id.web:
                // when web
                menuanswer = "weblist";
                mybookAdaptor = new BookAdaptor(weblist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;
            default:
                 // like all
                menuanswer = "booklist";

                mybookAdaptor = new BookAdaptor(booklist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;

        } //end switch

    }
    @Override
    public void OnBookClick(int position) {
        Intent i= new Intent(Library.this,BookPage.class);

        i.putExtra("Book", booklist.get(position));
        startActivity(i);

    }
}
