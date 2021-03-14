package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mobile_app.Adaptors.BookAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {
    //Initialize drawer
    DrawerLayout drawer;
    // reference to database
    DatabaseReference dbref;
    //Chosen category
    String menuanswer;
    ArrayList<Book> booklist = new ArrayList<>();
    RecyclerView rv_library;
    BookAdaptor mybookAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        drawer= findViewById(R.id.drawer_layout);
        // DataBase Reference
        dbref = FirebaseDatabase.getInstance().getReference("Book");
        rv_library = findViewById(R.id.recview);
        rv_library.setLayoutManager( new LinearLayoutManager(SearchResult.this));//Vertical view
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
            mybookAdaptor = new BookAdaptor(booklist);
            rv_library.setAdapter(mybookAdaptor);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


}