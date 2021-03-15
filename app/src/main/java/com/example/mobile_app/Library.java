package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.mobile_app.Adaptors.BookAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
// USED BY GREG DO NOT CHANGE !!!
public class Library extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    //Initialize drawer
    DrawerLayout drawer;
    //TODO: copy all recyclerView to LibRecyklerView and call LibRecyclerView when it is needed





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        drawer= findViewById(R.id.drawer_layout);

    }



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
