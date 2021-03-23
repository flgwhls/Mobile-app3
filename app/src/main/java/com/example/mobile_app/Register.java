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
    EditText studentID, firstName, lastNAme, email, password, confPassword;
    Button register;


    DatabaseReference dbref;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        // view of variables
        studentID = findViewById(R.id.et_reg_StudentID);
        firstName = findViewById(R.id.et_reg_FirstName);
        lastNAme = findViewById(R.id.et_reg_LastName);
        email = findViewById(R.id.et_reg_email);
        password = findViewById(R.id.et_reg_password);
        confPassword = findViewById(R.id.et_conf_password);
        register = findViewById(R.id.btn_reg_register);


        mFirebaseAuth = FirebaseAuth.getInstance();

        dbref = FirebaseDatabase.getInstance().getReference("Student");
        register.setOnClickListener(view -> {
            String emailID = email.getText().toString();
            String passwordID = password.getText().toString();
            Student s = new Student(studentID.getText().toString(), firstName.getText().toString(),
                    lastNAme.getText().toString(), email.getText().toString(),
                    password.getText().toString());

            //method to check if the field are empty or the password is less then 6 characters
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
                            startActivity(new Intent(Register.this, WelcomePage.class));

                        }
                    }


                });
            } else {
                Toast.makeText(Register.this, "Error occurred!", Toast.LENGTH_LONG).show();

            }


        });

    }
}