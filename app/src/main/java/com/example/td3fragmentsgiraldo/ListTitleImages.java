package com.example.td3fragmentsgiraldo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListTitleImages extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String flickr_reponse = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a566e0ab9da7898e5fdf4c03b60c4532&per_page=10&format=json&nojsoncallback=1&tags=cat";
    private ListView ListTitles;
    private ArrayList<String> reponseJSON = null;
    private RequestQueue mQueue;


    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListTitleImages() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListTitleImages newInstance(String param1, String param2) {
        ListTitleImages fragment = new ListTitleImages();
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

        final View rootView = inflater.inflate(R.layout.fragment_list_title_images, container, false);
        adapter= new ArrayAdapter<String>(getActivity(), R.layout.fragment_text_view);
        ListTitles = (ListView) rootView.findViewById(R.id.ListTitles);
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
        mQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, flickr_reponse, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jsonArray = response.getJSONObject("photos");
                            Log.i("SGB", String.valueOf(jsonArray));
                            JSONArray Arrayphotos = jsonArray.getJSONArray("photo");
                            Log.i("SGB", String.valueOf(Arrayphotos));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject image = Arrayphotos.getJSONObject(i);
                                String Imagetitle = image.getString("title");
                                reponseJSON.add(Imagetitle);
                                Log.i("SGB", Imagetitle);
                            }
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

        if(reponseJSON!=null){

            for (int i=0;i<reponseJSON.size();i++){
                adapter.add(reponseJSON.get(i));
                Log.i("SGB", reponseJSON.get(i));
            }
            ListTitles.setAdapter(adapter);
        }
    }
}
