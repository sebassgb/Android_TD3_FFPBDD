package com.example.td3fragmentsgiraldo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterImages extends RecyclerView.Adapter<AdapterImages.ViewHolder> {

    private List<ImageFlickr> imagesFlickr;
    private Context context;

    public AdapterImages(List<ImageFlickr> imagesFlickr, Context context  ) {
        this.imagesFlickr = imagesFlickr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // Log.i("SGB", "inside OnCreateViewHolder"+ ListimagesFlickr.get(0).getTitle());
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_flickr, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageFlickr imageFlickr = imagesFlickr.get(position);
       // Log.i("SGB", "inside Adapter ON Bind Holder"+ imageFlickr.getUrl());
       holder.textViewTitle.setText("Titre de l'image : "+imageFlickr.getTitle());
     //   Log.i("SGB", "inside Adapter ON Bind Holder"+ holder.textViewTitle.getText());
       holder.textViewAuthor.setText("Auteur de l'image : "+imageFlickr.getAuthor());
      //  Log.i("SGB", "inside Adapter ON Bind Holder"+ holder.textViewAuthor.getText());
       Picasso.get().load(imageFlickr.getUrl()).into(holder.imageViewflickr);
    }

    @Override
    public int getItemCount() {
        if(imagesFlickr!=null){
            return imagesFlickr.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageViewflickr;
        public TextView textViewTitle;
        public TextView textViewAuthor;


        public ViewHolder(View itemView){
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.imageTitle);
            textViewAuthor = (TextView) itemView.findViewById(R.id.imageAuthor);
            imageViewflickr = (ImageView) itemView.findViewById(R.id.imageViewFlickr);

        }
    }

}
