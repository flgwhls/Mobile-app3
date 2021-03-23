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
    // class to view books in Programming category
public class ProgBooksRecykler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    // variable declaration
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> progbooklist = new ArrayList<>(); //prog Books
    RecyclerView rv_progbooks; //recyclerView
    BookAdaptor progbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog_books_recykler);
        // firebase reference
        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference
            // join variables with layout
        rv_progbooks = findViewById(R.id.rv_prog_books);
        rv_progbooks.setLayoutManager(new LinearLayoutManager(ProgBooksRecykler.this));//Vertical view
        // create listener
        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                // check if book is in the Programmming category
                if (dss.getValue(Book.class).getCategory().equals("Programming")) {
                    // add book to the list
                    progbooklist.add(dss.getValue(Book.class));


                }
                // create adaptor
                progbookAdaptor = new BookAdaptor(progbooklist, ProgBooksRecykler.this);
                // set adaptor
                rv_progbooks.setAdapter(progbookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    // react on click
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(ProgBooksRecykler.this, BookPage.class);
        // send parcell
        i.putExtra("Book", progbooklist.get(position));
        // start BookPage Activity
        startActivity(i);
    }
}