package com.example.mobile_app;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class LibRecyclerView {
    // reference to database
    DatabaseReference dbref;
    ArrayList<Book> booklist = new ArrayList<>();
    RecyclerView rv_library_search;


}
