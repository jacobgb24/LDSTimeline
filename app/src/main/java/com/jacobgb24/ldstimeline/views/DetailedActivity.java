package com.jacobgb24.ldstimeline.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jacobgb24.ldstimeline.adapters.GalleryAdapter;
import com.jacobgb24.ldstimeline.adapters.SourceAdapter;
import com.jacobgb24.ldstimeline.model.Event;
import com.jacobgb24.ldstimeline.R;
import com.jacobgb24.ldstimeline.util.StaticMapBuilder;

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
        if(event == null) {
            event = (Event) getIntent().getExtras().get("EVENT");
        }

        title = (TextView) findViewById(R.id.details_title);
        title.setText(event.getName());
        date = (TextView) findViewById(R.id.details_date);
        date.setText(event.getDate());
        location = (TextView) findViewById(R.id.details_location);
        location.setText(event.getLocation());
        description = (TextView) findViewById(R.id.details_full_desc);
        description.setText(event.getDescription());

        mapPreview = (ImageView) findViewById(R.id.details_map);
        Glide.with(this).load(StaticMapBuilder.getStaticMapUrl(event)).fitCenter().into(mapPreview);
        mapPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapActivity.class);
                intent.putExtra("EVENT", event);
                v.getContext().startActivity(intent);
            }
        });

        sourceAdapter = new SourceAdapter(this, event.getSources());
        sources = (RecyclerView) findViewById(R.id.details_source_list);
        sources.setLayoutManager(new LinearLayoutManager(this));
        sources.setAdapter(sourceAdapter);

        galleryAdapter = new GalleryAdapter(this, event.getImages());
        images = (RecyclerView) findViewById(R.id.details_image_list);
        images.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        images.setAdapter(galleryAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
