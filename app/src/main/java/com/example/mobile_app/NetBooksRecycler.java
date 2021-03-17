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
public class NetBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> netbooklist = new ArrayList<>(); //net Books
    RecyclerView rv_netbooks; //recyclerView
    BookAdaptor netbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_books_recycler);
        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference

        rv_netbooks = findViewById(R.id.rv_net_books);
        rv_netbooks.setLayoutManager(new LinearLayoutManager(NetBooksRecycler.this));//Vertical view

        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                if (dss.getValue(Book.class).getCategory().equals("Network Design")) {
                    netbooklist.add(dss.getValue(Book.class));


                }
                netbookAdaptor = new BookAdaptor(netbooklist, NetBooksRecycler.this);
                rv_netbooks.setAdapter(netbookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(NetBooksRecycler.this, BookPage.class);

        i.putExtra("Book", netbooklist.get(position));
        startActivity(i);
    }
}