package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobile_app.Adaptors.Book4SellAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellBooks extends AppCompatActivity implements Book4SellAdapter.Book4SellHolder.OnBookClickListener {
    DatabaseReference dbref; //dbrefefence
    ArrayList<Book4Sell> sellbooklist = new ArrayList<>(); // boks for sale arrayList
    RecyclerView rv_sellbooks; //RecyklerView for display list of books
    Book4SellAdapter sellbookAdaptor; // Adaptor
    Button reg4Sell; // Button to register Book for Sell

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_books);
        // Database reference
        dbref = FirebaseDatabase.getInstance().getReference("Book4sell");
        rv_sellbooks = findViewById(R.id.rv_sell_books);
        reg4Sell = findViewById(R.id.btn_Register4Sell);
        rv_sellbooks.setLayoutManager(new LinearLayoutManager(SellBooks.this));
        reg4Sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regintent = new Intent(SellBooks.this, BookRegister.class);
                startActivity(regintent);
            }
        });
        dbref.addListenerForSingleValueEvent(listener);
    }

    // set listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {

                sellbooklist.add(dss.getValue(Book4Sell.class));
            }
            sellbookAdaptor = new Book4SellAdapter(sellbooklist, SellBooks.this);
            rv_sellbooks.setAdapter(sellbookAdaptor);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void OnBookClick(int position) {
        Intent i = new Intent(SellBooks.this, SellPage.class);

        i.putExtra("Book4Sell", sellbooklist.get(position));
        startActivity(i);
    }

}