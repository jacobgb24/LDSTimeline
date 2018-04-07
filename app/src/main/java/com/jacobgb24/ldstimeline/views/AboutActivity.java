package com.jacobgb24.ldstimeline.views;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.jacobgb24.ldstimeline.R;

/**
 * Created by jacob_000 on 4/7/2018.
 */

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView glide = (TextView) findViewById(R.id.license_glide);
        glide.setTag(R.string.license_path, "file:///android_asset/licenseGlide.html");
        glide.setTag(R.string.license_name, glide.getText().toString());
        glide.setOnClickListener(licenseClick);

        TextView photoView = (TextView) findViewById(R.id.license_photo);
        photoView.setTag(R.string.license_path, "file:///android_asset/licensePhotoView.html");
        photoView.setTag(R.string.license_name, photoView.getText().toString());
        photoView.setOnClickListener(licenseClick);

        TextView gson = (TextView) findViewById(R.id.license_gson);
        gson.setTag(R.string.license_path, "file:///android_asset/licenseGson.html");
        gson.setTag(R.string.license_name, gson.getText().toString());
        gson.setOnClickListener(licenseClick);
    }

    View.OnClickListener licenseClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("PATH", "="+((String)v.getTag(R.string.license_path)));
            WebView view = new WebView(getApplicationContext());
            view.loadUrl(((String)v.getTag(R.string.license_path)));
            AlertDialog alertDialog= new AlertDialog.Builder(v.getContext())
                    .setTitle(((String)v.getTag(R.string.license_name)))
                    .setView(view)
                    .setPositiveButton("ok", null)
                    .create();
            alertDialog.show();
        }
    };



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