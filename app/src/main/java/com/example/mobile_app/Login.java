package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextView signup;
    EditText emailID,passwordID;
    Button log;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup=findViewById(R.id.tv_reg_signup);
        emailID = findViewById(R.id.et_log_login);
        passwordID = findViewById(R.id.et_log_pasword);
        log = findViewById(R.id.btn_log_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if (mFirebaseUser !=null) {
                    Toast.makeText(Login.this, "Welcome In!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Login.this, "Please Login", Toast.LENGTH_LONG).show();
                }
            }
        };
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String password = passwordID.getText().toString();
                if (email.isEmpty()) {

                    emailID.setError("Please enter your password");
                    emailID.requestFocus();
                } else if (password.isEmpty()) {

                    passwordID.setError("Please enter your password");
                    passwordID.requestFocus();
                } else if (email.isEmpty() && password.isEmpty()) {

                    Toast.makeText(Login.this, "Fields are Empty", Toast.LENGTH_LONG).show();
                } else if (!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login error, please login again", Toast.LENGTH_LONG).show();

                            } else {
                                Intent IntoDashboard = new Intent(Login.this, Dashboard.class);
                                startActivity(IntoDashboard);
                            }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Error occurred!", Toast.LENGTH_LONG).show();

                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegPage = new Intent(Login.this, Register.class);
                startActivity(RegPage);
            }
        });
    }


   @Override
   protected void onStart() {
     super.onStart();
       mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }
}