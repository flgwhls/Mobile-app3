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
    // class to view books in Network Design category
public class NetBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    // Variable declaration
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> netbooklist = new ArrayList<>(); //net Books
    RecyclerView rv_netbooks; //recyclerView
    BookAdaptor netbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_books_recycler);
        // firebase reference
        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference
        // join variables with layout
        rv_netbooks = findViewById(R.id.rv_net_books);
        rv_netbooks.setLayoutManager(new LinearLayoutManager(NetBooksRecycler.this));//Vertical view
        // create listener
        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                // check if book is in Network Design category
                if (dss.getValue(Book.class).getCategory().equals("Network Design")) {
                    // add book to the list
                    netbooklist.add(dss.getValue(Book.class));


                }
                // create Adaptor
                netbookAdaptor = new BookAdaptor(netbooklist, NetBooksRecycler.this);
                // set adaptor
                rv_netbooks.setAdapter(netbookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    // react on click
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(NetBooksRecycler.this, BookPage.class);

        i.putExtra("Book", netbooklist.get(position));
        startActivity(i);
    }
}