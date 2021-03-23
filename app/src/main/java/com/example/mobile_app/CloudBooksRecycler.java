package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobile_app.Adaptors.BookAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
 // Class to View Books in Cloud category
public class CloudBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    // Variable Declaration
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> cloudbooklist = new ArrayList<>(); //cloud Books
    RecyclerView rv_cloudbooks; //recyclerView
    BookAdaptor cloudbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_books_recycler);
        // Firebase reference
        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference
        // Join variables with layout
        rv_cloudbooks = findViewById(R.id.rv_cloud_books);
        rv_cloudbooks.setLayoutManager(new LinearLayoutManager(CloudBooksRecycler.this));//Vertical view
        // Set Layout Manager
        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                if (dss.getValue(Book.class).getCategory().equals("Cloud Computing")) {
                    cloudbooklist.add(dss.getValue(Book.class));


                }
                // create adaptor
                cloudbookAdaptor = new BookAdaptor(cloudbooklist, CloudBooksRecycler.this);
                // Set adaptor
                rv_cloudbooks.setAdapter(cloudbookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    // react on click
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(CloudBooksRecycler.this, BookPage.class);
        // write parcel Book
        i.putExtra("Book", cloudbooklist.get(position));
        // start BookPage Activity
        startActivity(i);
    }
}