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
    // class to view books in Web Design category
public class WebBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    // Variable declaration
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> webbooklist = new ArrayList<>(); //web Books
    RecyclerView rv_webbooks; //recyclerView
    BookAdaptor webbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_books_recycler);
        // FireBase reference
        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference
        // join variables with layout
        rv_webbooks = findViewById(R.id.rv_web_books);
        rv_webbooks.setLayoutManager(new LinearLayoutManager(WebBooksRecycler.this));//Vertical view
        // create listener
        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                // check if book is in Web Design category
                if (dss.getValue(Book.class).getCategory().equals("Web Design")) {
                    // add book to list
                    webbooklist.add(dss.getValue(Book.class));
                }
                // create adaptor
                webbookAdaptor = new BookAdaptor(webbooklist, WebBooksRecycler.this);
                // set adaptor
                rv_webbooks.setAdapter(webbookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    // react on click
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(WebBooksRecycler.this, BookPage.class);

        i.putExtra("Book", webbooklist.get(position));
        startActivity(i);
    }
}