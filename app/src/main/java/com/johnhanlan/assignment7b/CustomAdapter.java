package com.johnhanlan.assignment7b;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by johnjhanlan on 2018-02-05.
 */

public class CustomAdapter extends ArrayAdapter<Article> {

    private ArrayList<Article> articles;
    private SharedPreferences sharedPreferences;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Article> articles, SharedPreferences sharedPreferences) {
        this(context, textViewResourceId, articles);
        this.sharedPreferences = sharedPreferences;
    }

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Article> articles) {
        super(context, textViewResourceId, articles);
        this.articles = articles;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        LayoutInflater johnsInflater = LayoutInflater.from(getContext());
        View customView = johnsInflater.inflate(R.layout.list_item, null);

        Article singleArticle = articles.get(position);
        TextView title = customView.findViewById(R.id.toptext);
        TextView description = customView.findViewById(R.id.bottomtext);
        TextView pubDate = customView.findViewById(R.id.pubText);

        title.setText(singleArticle.getTitle());
        description.setText(singleArticle.getDescription());
        pubDate.setText(singleArticle.getPubDate());

        if(sharedPreferences != null) {
            Helper.showPubDate(sharedPreferences, pubDate);
        }


        return customView;
    }
}
