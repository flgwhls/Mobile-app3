package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
/*
USED BY GREG DO NOT CHANGE !!!
BookPage class to view detail of tyhe book form book object
*/

public class BookPage extends AppCompatActivity {
    // Variable declaration
    // Arraylist to load all books
    ArrayList<Book> booklist = new ArrayList<>();
    // reference to database
    DatabaseReference dbref;
    TextView author, title, isbn, publisher, publicyear, edition, category;
    ImageView bookview;

    String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_page);
        // Activity to View book form database

        // link variables and layout
        title = findViewById(R.id.tv_booksellp_title2);
        author = findViewById(R.id.tv_booksellp_author2);
        isbn = findViewById(R.id.tv_booksellp_isbn2);
        publisher = findViewById(R.id.tv_booksellp_publisher2);
        publicyear = findViewById(R.id.tv_booksellp_publicyear2);
        bookview = findViewById(R.id.iv_booksellp_image);
        edition = findViewById(R.id.tv_booksellp_edition2);
        category = findViewById(R.id.tv_booksellp_category2);


        //Get book object from card
        Book b = getIntent().getParcelableExtra("Book");

        title.setText(b.getTitle());
        author.setText(b.getAuthor());
        isbn.setText(b.getISBN());
        publisher.setText(b.getPublisher());
        publicyear.setText(b.getPublicyear());
        edition.setText(b.getEdition());
        category.setText(b.getCategory());
        url = b.getImglink();
        // put picture ti imageView
        Glide.with(getApplicationContext()).load(url).into(bookview);

    }
}
