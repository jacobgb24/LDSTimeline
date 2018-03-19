package com.jacobgb24.ldstimeline.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.jacobgb24.ldstimeline.R;

import static android.R.attr.uiOptions;

/**
 * Created by jacob_000 on 3/18/2018.
 */

public class PhotoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        getSupportActionBar().setTitle(getIntent().getExtras().getString("INFO"));
        PhotoView photoView = findViewById(R.id.photo_view);
        Glide.with(this)
                .load(getIntent().getExtras().getString("URL"))
                .into(photoView);
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