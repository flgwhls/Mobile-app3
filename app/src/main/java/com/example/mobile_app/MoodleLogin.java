package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MoodleLogin extends AppCompatActivity {

    //Initialize website view
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodle_login);

        webView = (WebView) findViewById(R.id.webview_moodle);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://partnerships.moodle.roehampton.ac.uk/login/index.php");
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
}