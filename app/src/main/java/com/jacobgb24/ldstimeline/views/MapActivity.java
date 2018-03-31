package com.jacobgb24.ldstimeline.views;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jacobgb24.ldstimeline.R;
import com.jacobgb24.ldstimeline.model.Event;


public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putString("EVENT_ID", ((Event)getIntent().getExtras().get("EVENT")).getName());
        fragment.setArguments(bundle);
        fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
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

