package com.johnhanlan.assignment7b;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FullArticle extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvLink;
    private TextView tvPubDate;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_article);

        sharedPreferences = getSharedPreferences("general", 0);

        tvTitle       = findViewById(R.id.full_article_title);
        tvDescription = findViewById(R.id.full_article_description);
        tvLink        = findViewById(R.id.full_article_link);
        tvPubDate     = findViewById(R.id.full_article_pubDate);

        tvTitle.setText(
                Helper.articles
                .get(Helper.feed)
                .get(Helper.position)
                .getTitle()
        );

        tvDescription.setText(
                Helper.articles
                .get(Helper.feed)
                .get(Helper.position)
                .getDescription()
        );

        tvLink.setText(
                Helper.articles
                .get(Helper.feed)
                .get(Helper.position)
                .getLink()
        );

        tvPubDate.setText(
                Helper.articles
                .get(Helper.feed)
                .get(Helper.position)
                .getPubDate()
        );

        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FullArticle.this, ArticleWebView.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Helper.setBackgroundRelative(sharedPreferences, (RelativeLayout) findViewById(R.id.full_article_background));
    }
}
