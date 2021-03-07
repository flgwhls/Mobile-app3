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

        //database reference
        dbref = FirebaseDatabase.getInstance().getReference("Book");
        // Event Listener
//        dbref.addListenerForSingleValueEvent(listener);
        // btnright listener


        btnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < booklist.size() - 1) {
                    index++;
                    title.setText(booklist.get(index).getTitle());
                    author.setText(booklist.get(index).getAuthor());
                    isbn.setText(booklist.get(index).getISBN());
                    publisher.setText(booklist.get(index).getPublisher());
                    publicyear.setText(booklist.get(index).getPublicyear());
                    edition.setText(booklist.get(index).getEdition());
                    category.setText(booklist.get(index).getCategory());
                    url = booklist.get(index).getImglink();
                    Glide.with(getApplicationContext()).load(url).into(bookview);
                }


            }
        });

        // btnleft listener
        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;
                    title.setText(booklist.get(index).getTitle());
                    author.setText(booklist.get(index).getAuthor());
                    isbn.setText(booklist.get(index).getISBN());
                    publisher.setText(booklist.get(index).getPublisher());
                    publicyear.setText(booklist.get(index).getPublicyear());
                    edition.setText(booklist.get(index).getEdition());
                    category.setText(booklist.get(index).getCategory());
                    url = booklist.get(index).getImglink();
                    Glide.with(getApplicationContext()).load(url).into(bookview);

                }
            }
        });

    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {


                Book b = dss.getValue(Book.class);
                booklist.add(b);


            }
            title.setText(booklist.get(0).getTitle());
            author.setText(booklist.get(0).getAuthor());
            isbn.setText(booklist.get(0).getISBN());
            publisher.setText(booklist.get(0).getPublisher());
            publicyear.setText(booklist.get(0).getPublicyear());
            edition.setText(booklist.get(0).getEdition());
            category.setText(booklist.get(0).getCategory());
            url = booklist.get(0).getImglink();
            Glide.with(getApplicationContext()).load(url).into(bookview);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}
