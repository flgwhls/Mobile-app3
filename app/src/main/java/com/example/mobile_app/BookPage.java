package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
// USED BY GREG DO NOT CHANGE !!!
public class BookPage extends AppCompatActivity {
    // Variable declaration
    // Arraylist to load all books
    ArrayList<Book> booklist = new ArrayList<>();
    // reference to database
    DatabaseReference dbref;
    TextView author, title, isbn, publisher, publicyear, edition, category;
    ImageView bookview;
    Button btnleft, btnright;
    String url;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_page);
        // Activity to View book form database


        title = findViewById(R.id.tv_bookp_title2);
        author = findViewById(R.id.tv_bookp_author2);
        isbn = findViewById(R.id.tv_bookp_isbn2);
        publisher = findViewById(R.id.tv_bookp_publisher2);
        publicyear = findViewById(R.id.tv_bookp_publicyear2);
        bookview = findViewById(R.id.iv_bookp_image);
        btnright = findViewById(R.id.btn_bookp_right);
        btnleft = findViewById(R.id.btn_bookp_left);
        edition = findViewById(R.id.tv_bookp_edition2);
        category = findViewById(R.id.tv_bookp_category2);


        //Get book object from card
        //Book b= getIntent().getParcelableExtra("Book1");
        Book b = getIntent().getParcelableExtra("Book");

        title.setText(b.getTitle());
        author.setText(b.getAuthor());
        isbn.setText(b.getISBN());
        publisher.setText(b.getPublisher());
        publicyear.setText(b.getPublicyear());
        edition.setText(b.getEdition());
        category.setText(b.getCategory());
        url = b.getImglink();
        Glide.with(getApplicationContext()).load(url).into(bookview);

    }
}
