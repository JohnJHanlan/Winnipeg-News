package com.johnhanlan.assignment7b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleWebView extends AppCompatActivity {

    private WebView wvArticleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web_view);

        // Create the WebView
        wvArticleWebView = findViewById(R.id.articleWebView);

        // Load the URL
        wvArticleWebView.loadUrl(Helper
                .articles.get(Helper.feed)
                .get(Helper.position)
                .getLink());

        // Opens the link in the WebView
        wvArticleWebView.setWebViewClient(new WebViewClient());

        // Enable JavaScript for the WebView
        WebSettings webSettings = wvArticleWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);   // Might want to make this an option

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // Check if the key was the back button and if there is a Internet history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvArticleWebView.canGoBack()) {
            wvArticleWebView.goBack();
            return true;
        }

        // If it wasn't the back button or there wasn't a history
        // It will probably exit the activity
        return super.onKeyDown(keyCode, event);
    }
}
