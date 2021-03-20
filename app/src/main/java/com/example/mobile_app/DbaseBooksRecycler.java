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

public class DbaseBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> dbasebooklist = new ArrayList<>(); //dbase Books
    RecyclerView rv_dbasebooks; //recyclerView
    BookAdaptor dbasebookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbase_books_recycler);
        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference

        rv_dbasebooks = findViewById(R.id.rv_dbase_books);
        rv_dbasebooks.setLayoutManager(new LinearLayoutManager(DbaseBooksRecycler.this));//Vertical view

        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                if (dss.getValue(Book.class).getCategory().equals("Databases")) {
                    dbasebooklist.add(dss.getValue(Book.class));


                }
                dbasebookAdaptor = new BookAdaptor(dbasebooklist, DbaseBooksRecycler.this);
                rv_dbasebooks.setAdapter(dbasebookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(DbaseBooksRecycler.this, BookPage.class);

        i.putExtra("Book", dbasebooklist.get(position));
        startActivity(i);
    }
}