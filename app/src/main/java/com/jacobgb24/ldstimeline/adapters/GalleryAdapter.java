package com.jacobgb24.ldstimeline.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jacobgb24.ldstimeline.views.PhotoActivity;
import com.jacobgb24.ldstimeline.model.Pair;
import com.jacobgb24.ldstimeline.R;

import java.util.List;

/**
 * Created by jacob_000 on 3/17/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<Pair> images;
    private final LayoutInflater inflater;
    private Activity activity;


    public GalleryAdapter(Activity activity, List<Pair> images) {
        this.images = images;
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.list_item_image, viewGroup, false);
        return new ViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int pos) {
        holder.bind(images.get(pos));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private Activity activity;

        public ViewHolder(View v, Activity activity) {
            super(v);
            this.activity = activity;
            image = (ImageView) v.findViewById(R.id.list_image);


        }

        void bind(final Pair imagePair) {
            Glide.with(activity).load(imagePair.getValue()).centerCrop().override(500, 500).into(image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PhotoActivity.class);
                    intent.putExtra("URL", imagePair.getValue());
                    intent.putExtra("INFO", imagePair.getKey());
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

}


