package com.example.td3fragmentsgiraldo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterImages extends RecyclerView.Adapter<AdapterImages.ViewHolder> {

    private List<ImageFlickr> ListimagesFlickr;
    private Context context;

    public AdapterImages(List<ImageFlickr> imagesFlickr, Context context  ) {
        this.ListimagesFlickr = imagesFlickr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_flickr, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageFlickr imageFlickr = ListimagesFlickr.get(position);
       holder.textViewTitle.setText(imageFlickr.getTitle());
       holder.textViewAuthor.setText(imageFlickr.getAuthor());
       Picasso.get().load(imageFlickr.getUrl()).into(holder.imageViewflickr);
    }

    @Override
    public int getItemCount() {
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
