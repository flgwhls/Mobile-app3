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
    Button bookreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        // ---------------- TO DELETE WHEN not needed  ---------------
        bookpage = findViewById(R.id.btn_bookpage); // delete when not needed
        bookreg = findViewById(R.id.btn_reg_registerbook); //delete when not needed

        //-----------------------------------------------------------------------
        // temporary for test bookpage
        bookpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this,BookPage.class);

                startActivity(i);
            }
            //------------  END TEMPORARY bookPage test --------------------------


        });
        //------------ temporary for register books in firebase

        // delete when not needed
        bookreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this,BookRegister.class);

                startActivity(i);
            }
        });
        // ------- END of temporary book register
    }
}