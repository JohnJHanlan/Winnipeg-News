package com.johnhanlan.assignment7b;

import android.text.Html;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by johnjhanlan on 2018-02-05.
 */

class FreepHandler extends DefaultHandler {

    // Flags to keep track of what elements we are in
    private boolean inLink, inTitle, inPubDate, inDesc, inItem;

    private ArrayList<String> titles, descriptions, links, pubDates;

    private StringBuffer stringBuffer;

    //Initialization block
    {
        titles = new ArrayList<String>();
        descriptions = new ArrayList<String>();
        links = new ArrayList<String>();
        pubDates = new ArrayList<String>();
    }

    //Can probably get rid of this method. Using helper class now.
    public ArrayList<Article> getArticles() {

        ArrayList<Article> articles = new ArrayList<Article>();

        //titles.add("Test");

        //titles.trimToSize();

        for (int i = 0; i < titles.size(); i++) {
            articles.add(new Article(titles.get(i), descriptions.get(i), links.get(i), pubDates.get(i)));
        }

        return articles;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        Log.d("John", "startDoc");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.d("John", "endDoc");

        // Show whats in the arraylist
        Log.d("John", "DATA IN ARRAYLISTS:");
        for (String s : titles) {
            Log.d("John", s);
        }

        Log.d("John", "DESCRIPTIONS");
        for(String s: descriptions) {
            Log.d("John", s);
        }

        for (int i = 0; i < titles.size(); i++) {
            com.johnhanlan.assignment7b.Helper.freePressArticles.add(new Article(titles.get(i), descriptions.get(i), links.get(i), pubDates.get(i)));
        }

        //MainActivity.artical = new ArrayList<String>(titles);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        Log.d("John", "StartElement: " + qName);

        stringBuffer = new StringBuffer();

        if(qName.equals("title")) {
            inTitle = true;
        }

        if(qName.equals("description")) {
            inDesc = true;
        }

        if(qName.equals("link")) {
            inLink = true;
        }

        if(qName.equals("item")) {
            inItem = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        Log.d("John", "endElement: " + qName);

        if(inItem) {

            if (qName.equals("title")) {
                inTitle = false;
                titles.add(Html.fromHtml(stringBuffer.toString()).toString());
            }

            if (qName.equals("description")) {
                inDesc = false;
                descriptions.add(Html.fromHtml(stringBuffer.toString()).toString());
            }

            if (qName.equals("link")) {
                links.add(stringBuffer.toString());
            }

            if (qName.equals("pubDate")) {
                pubDates.add(stringBuffer.toString());
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        String s = new String(ch, start, length);

        stringBuffer.append(s);
        Log.d("John", "Characters: " + Html.fromHtml(s));

        if(inTitle) {
            //titles.add(s);
        }

        if(inDesc) {

            //descriptions.add(s);
        }
    }

}
