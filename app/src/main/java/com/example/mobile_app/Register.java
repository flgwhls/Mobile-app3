package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {
  // Variables
    String firstName,lastNAme, email, password, confPassword;
    Button register;
    // for testing bookpage
    Button bookpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bookpage = findViewById(R.id.btn_bookpage); // delete when not needed

        //-----------------------------------------------------------------------
        // temporary for test bookpage
        bookpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this,BookPage.class);



                startActivity(i);
            }
            //------------  END TEMPORARY bookPage test --------------------------
            // delete when not needed

        });

    }
}