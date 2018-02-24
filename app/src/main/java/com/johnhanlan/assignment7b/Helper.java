package com.johnhanlan.assignment7b;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by johnjhanlan on 2018-02-09.
 */

public final class Helper {

    //Feeds
    public static ArrayList<Article> localArticles = new ArrayList<Article>();
    public static ArrayList<Article> breakingArticles = new ArrayList<Article>();
    public static ArrayList<Article> worldArticles = new ArrayList<Article>();

    public static ArrayList<ArrayList<Article>> articles = new ArrayList<ArrayList<Article>>();

    //positions
    public static int position = 0;
    public static int feed = 0;

    private Helper() {
        //localArticles = new ArrayList<Article>();
    }

    public static void setBackgroundLinear(SharedPreferences sharedPreferences, LinearLayout background){
        switch (sharedPreferences.getString("background_colour", "blank")){
            case "gray":
                background.setBackgroundColor(Color.GRAY);
                break;

            case "white":
                background.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    public static void setBackgroundRelative(SharedPreferences sharedPreferences, RelativeLayout background){
        switch (sharedPreferences.getString("background_colour", "blank")){
            case "gray":
                background.setBackgroundColor(Color.GRAY);
                break;

            case "white":
                background.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    public static void showPubDate(SharedPreferences sharedPreferences, TextView textView) {
        if(sharedPreferences.getBoolean("hide_pub_date", false)) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }

}
