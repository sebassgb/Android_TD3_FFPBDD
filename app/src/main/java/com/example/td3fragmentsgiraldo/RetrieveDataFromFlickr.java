package com.example.td3fragmentsgiraldo;

import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RetrieveDataFromFlickr extends AsyncTask<String, Void, JSONObject> {

    private Fragment Myfragment;

    public RetrieveDataFromFlickr(Fragment fragment) {
        Myfragment = fragment;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {


            //Create a URL object holding our url

          /*  URL myUrl = new URL("http://www.flickr.com/services/feeds/photos_public.gne?"
                    + "tags=cats"
                    + "&format=json");*/

        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a566e0ab9da7898e5fdf4c03b60c4532&per_page=10&format=json&nojsoncallback=1&tags=cat");
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream

            result = readStream(in); // Read stream
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        JSONObject json = null;
        try {

            json = new JSONObject(result);
            Log.i("SGB", String.valueOf(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json; // returns the result

    }


    @Override
    protected void onPostExecute(JSONObject s) {
/*
        ListView listImages = (ListView)myActivity.findViewById(R.id.ListImagesFlickr);
        BmAdapter array = new BmAdapter(listImages.getContext());
        listImages.setAdapter(array);

        try {
            JSONArray items = s.getJSONArray("items");
            for (int i = 0; i<items.length(); i++)
            {
                JSONObject flickr_entry = items.getJSONObject(i);
                String urlmedia = flickr_entry.getJSONObject("media").getString("m");
                Log.i("CIO", "URL media: " + urlmedia);

                // Downloading image
                BitmapDownloader abd = new BitmapDownloader(array);
                abd.execute(urlmedia);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/


    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();

        // Extracting the JSON object from the String
       String jsonextracted = sb.substring("jsonFlickrFeed(".length(), sb.length() - 1);
        Log.i("SGB", String.valueOf(sb));
        return jsonextracted;
    }


}