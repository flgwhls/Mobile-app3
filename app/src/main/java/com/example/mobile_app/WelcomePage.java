package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomePage extends AppCompatActivity {

    Button Register,Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);



        Button Register=findViewById(R.id.btn_register);
        Button Login=findViewById(R.id.btn_login);


       Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent IntoRegisterPage=new Intent(WelcomePage.this,Register.class);
               startActivity(IntoRegisterPage);
           }
       });
       Login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent IntoLoginPage=new Intent(WelcomePage.this,Login.class);
               startActivity(IntoLoginPage);
           }
       });
    }
}

