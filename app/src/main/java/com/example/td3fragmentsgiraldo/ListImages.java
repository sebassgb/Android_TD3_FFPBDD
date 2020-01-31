package com.example.td3fragmentsgiraldo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.collection.LruCache;
import androidx.fragment.app.Fragment;

import android.service.autofill.Transformation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListImages extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String flickr_reponse = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a566e0ab9da7898e5fdf4c03b60c4532&per_page=10&format=json&nojsoncallback=1&tags=cat";
    public ListView list;
    public BitmapAdapter tableau;
    private RequestQueue mQueue;
    ArrayList<String> urlImgsParsed = new ArrayList<String>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListImages() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListImages newInstance(String param1, String param2) {
        ListImages fragment = new ListImages();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_list_images, container, false);
        list = (ListView)rootView.findViewById(R.id.ListImages);
        tableau = new BitmapAdapter(list.getContext());
        list.setAdapter(tableau);
        jsonParserFlickr();
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void jsonParserFlickr(){
        //http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
        mQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, flickr_reponse, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonArray = response.getJSONObject("photos");
                           // Log.i("SGB", String.valueOf(jsonArray));
                            JSONArray Arrayphotos = jsonArray.getJSONArray("photo");
                            //Log.i("SGB", String.valueOf(Arrayphotos));
                            for (int i = 0; i < Arrayphotos.length(); i++) {
                                JSONObject image = Arrayphotos.getJSONObject(i);
                                String farm_id = image.getString("farm");
                                String server_id = image.getString("server");
                                String id = image.getString("id");
                                String secret = image.getString("secret");
                                urlImgsParsed.add("http://farm"+farm_id+".staticflickr.com/"+server_id+"/"+id+"_"+secret+".jpg");
                                Log.i("SGB", urlImgsParsed.get(i));
                            }
                            createImages(urlImgsParsed);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }

    private void createImages(ArrayList<String> urlImgsParsed) {

            for (int i = 0; i<urlImgsParsed.size(); i++) {

                String urlmedia = urlImgsParsed.get(i);

                // Downloading image
                AsyncBitmapDownloader abd = new AsyncBitmapDownloader(tableau);
                abd.execute(urlmedia);
                list.setAdapter(tableau);
            }

    }
}

