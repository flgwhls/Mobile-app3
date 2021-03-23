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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.mobile_app.Adaptors.BookAdaptor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
// Class to view and manage Library

public class Library extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    // variable declaration
    DrawerLayout drawer;
    Button btnbooks4sell;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        // Firebase reference
        firebaseAuth = FirebaseAuth.getInstance();
        // join variable with layout
        drawer= findViewById(R.id.drawer_layout);
        btnbooks4sell = findViewById(R.id.btn_book4sell);
        //set listener
        btnbooks4sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sellb = new Intent(Library.this,SellBooks.class);
                // start SellBooks Activity
                startActivity(sellb);
            }
        });

    }


    // menu
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
        Dashboard.redirectActivity(this,Recycler_view_activities.class);
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
                Dashboard.redirectActivity(Library.this, WelcomePage.class);
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
    protected void onPause(){
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }
    // MEnu
    public void showMenu(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    // use to get answer and view cards in RecyclerView
    @Override
    public boolean onMenuItemClick(MenuItem item){
        // select menu item
        switch (item.getItemId()){
          /*  case R.id.all:
                // when all
                menuanswer = "all";
                mybookAdaptor = new BookAdaptor(booklist,Library.this);
                rv_library.setAdapter(mybookAdaptor);
                return true;*/
            case R.id.cloud:
                // when cloud

                Intent cloudb= new Intent(Library.this,CloudBooksRecycler.class);
                startActivity(cloudb);
                return true;
            case R.id.graphics:
                // when graphics

                Intent graphb= new Intent(Library.this,GraphBooksRecycler.class);
                startActivity(graphb);
                return true;
            case R.id.network:
                // when network
                Intent netb = new Intent(Library.this,NetBooksRecycler.class);
                startActivity(netb);
                return true;
            case R.id.dbase:
                // when network
                Intent dbaseb= new Intent(Library.this,DbaseBooksRecycler.class);
                startActivity(dbaseb);

                return true;
            case R.id.programming:
                // when programming
                Intent progb= new Intent(Library.this,ProgBooksRecykler.class);
                startActivity(progb);
                return true;
            case R.id.web:
                // when web
                Intent webb= new Intent(Library.this,WebBooksRecycler.class);
                startActivity(webb);
                return true;
            default:
                 // like all
               
                Intent allb= new Intent(Library.this,AllBooksRecycler.class);
                startActivity(allb);


                return true;

        } //end switch



        // After switch booklist contain object in selected categoy
        // run adaptor to view cards

    }

}
