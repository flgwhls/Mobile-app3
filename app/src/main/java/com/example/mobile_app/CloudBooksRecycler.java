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

public class CloudBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> cloudbooklist = new ArrayList<>(); //cloud Books
    RecyclerView rv_cloudbooks; //recyclerView
    BookAdaptor cloudbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cloud_books_recycler);

        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference

        rv_cloudbooks = findViewById(R.id.rv_cloud_books);
        rv_cloudbooks.setLayoutManager(new LinearLayoutManager(CloudBooksRecycler.this));//Vertical view

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
                cloudbookAdaptor = new BookAdaptor(cloudbooklist, CloudBooksRecycler.this);
                rv_cloudbooks.setAdapter(cloudbookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(CloudBooksRecycler.this, BookPage.class);

        i.putExtra("Book", cloudbooklist.get(position));
        startActivity(i);
    }
}