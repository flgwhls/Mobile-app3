package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
  // Variables

    EditText studentID,firstName,lastNAme, email, password, confPassword;
    Button register;
    // for testing bookpage
    Button bookpage;
    Button bookreg;

    DatabaseReference dbref;
    FirebaseAuth mFirebaseAuth;

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



        mFirebaseAuth = FirebaseAuth.getInstance();

        dbref = FirebaseDatabase.getInstance().getReference("Student");
        register.setOnClickListener(view -> {
            String emailID = email.getText().toString();
            String passwordID = password.getText().toString();
            Student s = new Student(studentID.getText().toString(), firstName.getText().toString(),
                    lastNAme.getText().toString(), email.getText().toString(),
                    password.getText().toString());
            // prova
            if (!TextUtils.isEmpty(studentID.getText().toString())
                    && !TextUtils.isEmpty(firstName.getText().toString())
                    && !TextUtils.isEmpty(lastNAme.getText().toString())
                    && !TextUtils.isEmpty(email.getText().toString())
                    && !TextUtils.isEmpty(password.getText().toString())
                    && !TextUtils.isEmpty(confPassword.getText().toString())
                    && password.getText().toString().compareTo(confPassword.getText().toString()) == 0
                    && password.getText().toString().length() >= 6) {


                dbref.child(dbref.push().getKey()).setValue(s);



                mFirebaseAuth.createUserWithEmailAndPassword(emailID, passwordID).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Sign up unsuccessful! Please try again", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(Register.this, Dashboard.class));

                        }
                    }


                });
            }
                else

            {
                Toast.makeText(Register.this, "Error occurred!", Toast.LENGTH_LONG).show();

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
                Intent i = new Intent(Register.this, BookRegister.class);

                startActivity(i);
            }
        });
        // ------- END of temporary book register
    }
}