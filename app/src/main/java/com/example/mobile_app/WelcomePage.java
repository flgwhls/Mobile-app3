package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomePage extends AppCompatActivity {

    //variables
    Button Register, Login, MoodleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        // variables views
        Register = findViewById(R.id.btn_register);
        Login = findViewById(R.id.btn_login);
        MoodleLogin = findViewById(R.id.btn_wp_moodle);


        //configuration of register button to display the page to sign up
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntoRegisterPage = new Intent(WelcomePage.this, Register.class);
                startActivity(IntoRegisterPage);
            }
        });

        //configuration of login button to display login page
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntoLoginPage = new Intent(WelcomePage.this, Login.class);
                startActivity(IntoLoginPage);
            }
        });

        //configuration of botton to get web view of Moodle page
        MoodleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomePage.this, moodle_login.class);
                startActivity(i);
            }
        });
    }
}

