package com.jacobgb24.ldstimeline.Activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacobgb24.ldstimeline.Adapters.GalleryAdapter;
import com.jacobgb24.ldstimeline.Adapters.SourceAdapter;
import com.jacobgb24.ldstimeline.Adapters.TimelineAdapter;
import com.jacobgb24.ldstimeline.Model.Dao;
import com.jacobgb24.ldstimeline.Model.Event;
import com.jacobgb24.ldstimeline.R;

public class DetailedActivity extends AppCompatActivity {
    private Event event;
    private TextView title;
    private TextView date;
    private TextView location;
    private TextView description;
    private RecyclerView images;
    private GalleryAdapter galleryAdapter;
    private ImageView mapPreview;
    private RecyclerView sources;
    private SourceAdapter sourceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        event = (Event) getIntent().getExtras().get("EVENT");

        title = (TextView) findViewById(R.id.details_title);
        title.setText(event.getName());
        date = (TextView) findViewById(R.id.details_date);
        date.setText(event.getDate());
        location = (TextView) findViewById(R.id.details_location);
        location.setText(event.getLocation());
        description = (TextView) findViewById(R.id.details_full_desc);
        description.setText(event.getDescription());

        sourceAdapter = new SourceAdapter(this, event.getSources());
        sources = (RecyclerView) findViewById(R.id.details_source_list);
        sources.setLayoutManager(new LinearLayoutManager(this));
        sources.setAdapter(sourceAdapter);

        galleryAdapter = new GalleryAdapter(this, event.getImages());
        images = (RecyclerView) findViewById(R.id.details_image_list);
        images.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        images.setAdapter(galleryAdapter);
    }
}
