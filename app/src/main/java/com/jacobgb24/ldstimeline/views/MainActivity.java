package com.jacobgb24.ldstimeline.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jacobgb24.ldstimeline.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private Fragment timelineFragment;
    private Fragment mapFragment;

    private enum FRAG {TIMELINE, MAP}

    private FRAG loadedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        timelineFragment = new TimelineFragment();
        mapFragment = new MapFragment();
        fm.beginTransaction().add(R.id.fragment_container, timelineFragment).add(R.id.fragment_container, mapFragment).hide(mapFragment).commit();
        loadedFragment = FRAG.TIMELINE;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;    // return true to display menu
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (loadedFragment == FRAG.TIMELINE) {
            menu.findItem(R.id.action_timeline).setVisible(false);
            menu.findItem(R.id.action_map).setVisible(true);
        }
        else {
            menu.findItem(R.id.action_timeline).setVisible(true);
            menu.findItem(R.id.action_map).setVisible(false);
        }
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_map:
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(timelineFragment).show(mapFragment).commit();
                loadedFragment = FRAG.MAP;
                invalidateOptionsMenu();
                return true;
            case R.id.action_timeline:
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(mapFragment).show(timelineFragment).commit();
                loadedFragment = FRAG.TIMELINE;
                invalidateOptionsMenu();
                return true;
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

