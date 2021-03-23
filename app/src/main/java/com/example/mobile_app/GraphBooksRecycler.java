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
    // clase to view books in computer graphics category
public class GraphBooksRecycler extends AppCompatActivity implements BookAdaptor.BookHolder.OnBookClickListener{
    // variable declaration
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book> graphbooklist = new ArrayList<>(); //graph Books
    RecyclerView rv_graphbooks; //recyclerView
    BookAdaptor graphbookAdaptor; //Adaptor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_books_recycler);
        // firebase reference
        dbref = FirebaseDatabase.getInstance().getReference("Book"); //Firebase reference
        // join variable with layout
        rv_graphbooks = findViewById(R.id.rv_graph_books);
        rv_graphbooks.setLayoutManager(new LinearLayoutManager(GraphBooksRecycler.this));//Vertical view
        // create listener
        dbref.addListenerForSingleValueEvent(listener);
    }
    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                // check if book is in Computer graphics category
                if (dss.getValue(Book.class).getCategory().equals("Computer Graphics")) {
                    // add book to list
                    graphbooklist.add(dss.getValue(Book.class));


                }
                // create adaptor
                graphbookAdaptor = new BookAdaptor(graphbooklist, GraphBooksRecycler.this);
                // set adaptor
                rv_graphbooks.setAdapter(graphbookAdaptor);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    // react on click
    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(GraphBooksRecycler.this, BookPage.class);

        i.putExtra("Book", graphbooklist.get(position));
        startActivity(i);
    }
}