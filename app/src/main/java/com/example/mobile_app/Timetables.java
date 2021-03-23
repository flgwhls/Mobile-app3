package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.auth.FirebaseAuth;

public class Timetables extends AppCompatActivity {
    //Initialize website view
    private WebView webView;

    FirebaseAuth firebaseAuth;

    //Initialize drawer
    DrawerLayout drawer;

    //show timetable content when selected
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetables);
        drawer = findViewById(R.id.drawer_layout);

        firebaseAuth = FirebaseAuth.getInstance();

        //call selected webview url, allow access to javasript and website local storage
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://ttportalqalive.com/2021/studentlogin.html");
    }

    //back button to works us a browser back button
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            ;
        } else {
            super.onBackPressed();
        }
    }

    public void ClickMenu(View view) {
        //Open drawer
        Dashboard.openDrawer(drawer);
    }

    public void ClickHome(View view) {
        Dashboard.redirectActivity(this, Dashboard.class);
    }

    public void ClickLibrary(View view) {
        Dashboard.redirectActivity(this, Library.class);
    }

    public void ClickTimetables(View view) {
        recreate();
    }

    public void ClickFloorMap(View view) {
        Dashboard.redirectActivity(this, FloorMap.class);
    }

    public void ClickForum(View view) {
        Dashboard.redirectActivity(this, ForumRecycleView.class);
    }

    public void ClickActivities(View view) {
        Dashboard.redirectActivity(this, Recycler_view_activities.class);
    }

    //logout method
    public void ClickLogout(View view) {
        //Initialise alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("Are you sure you want to log out?");
        //Positive answer button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Logout user
                firebaseAuth.signOut();
                //redirect activity to welcome page
                Dashboard.redirectActivity(Timetables.this, WelcomePage.class);
            }
        });
        //Negative answer button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }
}