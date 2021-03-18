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
// USED BY GREG DO NOT CHANGE !!!
public class AllBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> allbooklist = new ArrayList<>(); //all Books
    RecyclerView rv_allbooks; //recyclerView
    BookAdaptor allbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books_recycler);

        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference

        rv_allbooks = findViewById(R.id.rv_all_books);
        rv_allbooks.setLayoutManager( new LinearLayoutManager(AllBooksRecycler.this));//Vertical view

        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss: snapshot.getChildren()){
                allbooklist.add(dss.getValue(Book.class)); //Put all book



            }
            allbookAdaptor= new BookAdaptor(allbooklist,AllBooksRecycler.this);
            rv_allbooks.setAdapter(allbookAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(AllBooksRecycler.this, BookPage.class);

        i.putExtra("Book", allbooklist.get(position));
        startActivity(i);
    }

}

