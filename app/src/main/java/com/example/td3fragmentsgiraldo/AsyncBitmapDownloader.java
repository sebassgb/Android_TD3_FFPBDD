package com.example.td3fragmentsgiraldo;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.picasso.Picasso;
import java.io.IOException;


public class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {

    BitmapAdapter adapter_ = null;
    Bitmap bm = null;

    public AsyncBitmapDownloader(BitmapAdapter adapter) {
        adapter_ = adapter;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            bm = Picasso.get().load(strings[0]).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.i("SGB", "Image received !");
        adapter_.add(bitmap);
        adapter_.notifyDataSetChanged();
    }
}