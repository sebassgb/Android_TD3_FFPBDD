package com.example.td3fragmentsgiraldo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TextViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String flickr_reponse = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a566e0ab9da7898e5fdf4c03b60c4532&per_page=10&format=json&nojsoncallback=1&tags=cat";

    TextView reponseJSON;

    private OnFragmentInteractionListener mListener;

    public TextViewFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TextViewFragment newInstance(String param1, String param2) {
        TextViewFragment fragment = new TextViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        final View rootView = inflater.inflate(R.layout.fragment_text_view, container, false);
       reponseJSON = (TextView) rootView.findViewById(R.id.textJSON);
       RequestQueue queue = Volley.newRequestQueue(getActivity());
       StringRequest stringRequest = new StringRequest(Request.Method.GET, flickr_reponse,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        reponseJSON.setText(response);
                        Log.i("SGB", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("SGB", "Error return request");
            }
        });
        queue.add(stringRequest);



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
}
