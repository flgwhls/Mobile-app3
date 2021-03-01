package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class BookRegister extends AppCompatActivity {
    EditText title, author, edition, isbn, category, imagelink;
    Button bookregister;
    FirebaseDatabase dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_register);

        title = findViewById(R.id.et_bookreg_title);
        author = findViewById(R.id.et_bookreg_author);
        isbn = findViewById(R.id.et_bookreg_ISBN);
        category = findViewById(R.id.et_bookreg_category);
        imagelink = findViewById(R.id.et_bookreg_imageLink);

        bookregister = findViewById(R.id.btn_reg_registerbook);
        // reference to fireBAse
        dbref= FirebaseDatabase.getInstance().getReference("Book");
        // Action after click bookregister button
        bookregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book b=new Book(etname.getText().toString(),etsurname.getText().toString(),etprice.getText().toString(),imglink.getText().toString());

                dbref.child(dbref.push().getKey()).setValue(b);
            }
        });



    }
}