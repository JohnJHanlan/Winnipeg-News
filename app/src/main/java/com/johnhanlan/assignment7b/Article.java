package com.johnhanlan.assignment7b;

/**
 * Created by johnjhanlan on 2018-02-05.
 */

public class Article {

    private String title;
    private String description;
    private String link;
    private String pubDate;

    public Article(String title, String description, String link, String pubDate) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() { return link; }

    public String getPubDate() { return pubDate; }
}
