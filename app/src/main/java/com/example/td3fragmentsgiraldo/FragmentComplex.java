package com.example.td3fragmentsgiraldo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentComplex extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String flickr_reponse = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a566e0ab9da7898e5fdf4c03b60c4532&per_page=10&format=json&nojsoncallback=1&tags=cat";
    private RequestQueue mQueue;
    private RecyclerView recyclerViewImages;
    private RecyclerView.Adapter adapterComplex;
    private List<ImageFlickr> ImagesFlickr;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentComplex() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentComplex newInstance(String param1, String param2) {
        FragmentComplex fragment = new FragmentComplex();
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
        final View rootView = inflater.inflate(R.layout.fragment_complex, container, false);
        recyclerViewImages = (RecyclerView) rootView.findViewById(R.id.rvImages);
        configureRecyclerView();
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


    private void configureRecyclerView(){
        // Create adapter passing in the sample user data
        adapterComplex = new AdapterImages(ImagesFlickr, getContext());
        // Attach the adapter to the recyclerview to populate items
        recyclerViewImages.setAdapter(adapterComplex);
        // Set layout manager to position the items
        recyclerViewImages.setLayoutManager(new LinearLayoutManager(getActivity()));
        ImagesFlickr = new ArrayList<>();

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
                            JSONArray Arrayphotos = jsonArray.getJSONArray("photo");
                            for (int i = 0; i < Arrayphotos.length(); i++) {
                                JSONObject image = Arrayphotos.getJSONObject(i);

                                String farm_id = image.getString("farm");
                                String server_id = image.getString("server");
                                String id = image.getString("id");
                                String secret = image.getString("secret");
                                String url = "http://farm"+farm_id+".staticflickr.com/"+server_id+"/"+id+"_"+secret+".jpg";
                                ImageFlickr imageF = new ImageFlickr(image.getString("title"),
                                        image.getString("owner"),
                                        image.getString("id"),
                                        image.getString("secret"),
                                        image.getString("server"),
                                        url,
                                        image.getString("farm"));
                                ImagesFlickr.add(imageF);
                                adapterComplex = new AdapterImages(ImagesFlickr, getContext());
                            }

                            recyclerViewImages.setAdapter(adapterComplex);
                          //  Log.i("SGB", "inside Adapter Images constructor"+ adapterComplex.getItemCount());

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

}
