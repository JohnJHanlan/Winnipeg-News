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
        wvArticleWebView.loadUrl(Helper.localArticles.get(Helper.position).getLink());

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

//    // Check if the link clicked is the one specified.
//    private class MyWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            //return super.shouldOverrideUrlLoading(view, request);
//
//            // Check for the proper website
//            if (Uri.parse(url).getHost().equals(Helper.localArticles.get(Helper.position).getLink())) {
//                return false;
//            }
//
//            // Otherwise open the default Web Browser
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Helper.localArticles.get(Helper.position).getLink()));
//            startActivity(intent);
//            return true;
//        }
//    }
}
