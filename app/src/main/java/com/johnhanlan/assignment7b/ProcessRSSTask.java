package com.johnhanlan.assignment7b;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by johnjhanlan on 2018-02-05.
 */

public class ProcessRSSTask extends AsyncTask<Void, Void, Void> {

    private FreepHandler freepHandler;
    private ArrayList<String> urls;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("John", "onPreExecute");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("John", "doInBackground");

        urls = new ArrayList<String>();
        urls.add("https://www.winnipegfreepress.com/rss/?path=%2Flocal");
        urls.add("https://www.winnipegfreepress.com/rss/?path=%2Fbreakingnews");
        urls.add("https://www.winnipegfreepress.com/rss/?path=%2Fworld");

        int counter = 1;

        for (String currentUrl: urls) {

            URL url = null;
            try {
                url = new URL(currentUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            SAXParser saxParser = null;
            try {
                saxParser = SAXParserFactory.newInstance().newSAXParser();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // create instance of our FreepHandler
            freepHandler = new FreepHandler(counter++);

            try {
                saxParser.parse(inputStream, freepHandler);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("John", "onPostExecute");
    }

} //ProcessRAATask
