package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookRegister extends AppCompatActivity {
    EditText title, author, edition, isbn, category, imagelink;
    Button bookregister;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_register);

        title= findViewById(R.id.et_bookreg_title);
        author = findViewById(R.id.et_bookreg_author);
        edition = findViewById(R.id.et_bookreg_edition);
        isbn = findViewById(R.id.et_bookreg_ISBN);
        category = findViewById(R.id.et_bookreg_category);
        imagelink = findViewById(R.id.et_bookreg_ImageLink);
        bookregister = findViewById(R.id.btn_bookreg_register);

        dbref = FirebaseDatabase.getInstance().getReference("Book");
        bookregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book b = new Book(title.getText().toString(),author.getText().toString(),
                        edition.getText().toString(),isbn.getText().toString(),
                        category.getText().toString(),imagelink.getText().toString());
                dbref.child(dbref.push().getKey()).setValue(b);
                Intent i = new Intent(BookRegister.this,Register.class);



            }
        });






    }
}