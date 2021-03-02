package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
  // Variables
   // String studentID,firstName,lastNAme, email, password, confPassword;
    EditText studentID,firstName,lastNAme, email, password, confPassword;
    Button register;
    // for testing bookpage
    Button bookpage;
    Button bookreg;

    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        // ---------------- TO DELETE WHEN not needed  ---------------
        studentID = findViewById(R.id.et_reg_StudentID);
        firstName = findViewById(R.id.et_reg_FirstName);
        lastNAme = findViewById(R.id.et_reg_LastName);
        email = findViewById(R.id.et_reg_email);
        password = findViewById(R.id.et_reg_password);
        confPassword = findViewById(R.id.et_conf_password);
        register= findViewById(R.id.btn_reg_register);
        bookpage = findViewById(R.id.btn_bookpage); // delete when not needed
        bookreg = findViewById(R.id.btn_reg_registerbook); //delete when not needed



        dbref = FirebaseDatabase.getInstance().getReference("Student");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student s = new Student(studentID.getText().toString(),firstName.getText().toString(),
                        lastNAme.getText().toString(),email.getText().toString(),
                        password.getText().toString());
                dbref.child(dbref.push().getKey()).setValue(s);
                Intent i = new Intent(Register.this,Dashboard.class);
                startActivity(i);


            }
        });

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