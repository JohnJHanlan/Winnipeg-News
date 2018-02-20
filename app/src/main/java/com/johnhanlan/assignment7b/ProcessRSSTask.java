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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("John", "onPreExecute");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("John", "doInBackground");

        URL url = null;
        try {
            url = new URL("https://www.winnipegfreepress.com/rss/?path=%2Flocal");
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
            connection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Possible fix to special characters
        //Don't need this, stringBuffer fixed the bug
        //InputSource is = new InputSource(url.openStream()); is.setEncoding("ISO-8859-1"); xr.parse(is);

        // create instance of our FreepHandler
        freepHandler = new FreepHandler();

        try {
            saxParser.parse(inputStream, freepHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Probably can get rid of this since using helper class now.
    public ArrayList<Article> getArticles() {
        return freepHandler.getArticles();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("John", "onPostExecute");
    }

} //ProcessRAATask
