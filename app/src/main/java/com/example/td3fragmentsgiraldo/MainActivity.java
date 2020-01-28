package com.example.td3fragmentsgiraldo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextViewFragment.OnFragmentInteractionListener, ListImages.OnFragmentInteractionListener, ListTitleImages.OnFragmentInteractionListener, FragmentComplex.OnFragmentInteractionListener {

    Button fragmentTextView, fragmentComplex, fragmentListTitle, fragmentListImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextViewFragment fragmento1 = new TextViewFragment();

       // Bundle bundle = new Bundle();
        //bundle.putString("json_reponse", String.valueOf("test");
        //fragmento1.setArguments(bundle);

       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragmento1);
        transaction.commit();

        fragmentTextView = (Button) findViewById(R.id.buttonFragment1);
        fragmentComplex = (Button) findViewById(R.id.buttonFragment2);
        fragmentListTitle = (Button) findViewById(R.id.buttonFragment3);
        fragmentListImages = (Button) findViewById(R.id.buttonFragment4);

        fragmentTextView.setOnClickListener(this);
        fragmentComplex.setOnClickListener(this);
        fragmentListTitle.setOnClickListener(this);
        fragmentListImages.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonFragment1:
                TextViewFragment fragmento1 = new TextViewFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameContainer, fragmento1);
                transaction.commit();
                break;

            case R.id.buttonFragment2:
                ListTitleImages fragmento2 = new ListTitleImages();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.frameContainer, fragmento2);
                transaction2.commit();
                break;

            case R.id.buttonFragment3:
                ListImages fragmento3 = new ListImages();
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.frameContainer, fragmento3);
                transaction3.commit();
                break;

            case R.id.buttonFragment4:
                FragmentComplex fragmento4 = new FragmentComplex();
                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                transaction4.replace(R.id.frameContainer, fragmento4);
                transaction4.commit();
                break;




        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
