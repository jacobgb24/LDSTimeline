package com.jacobgb24.ldstimeline.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jacobgb24.ldstimeline.Adapters.TimelineAdapter;
import com.jacobgb24.ldstimeline.Model.Dao;
import com.jacobgb24.ldstimeline.R;

public class MainActivity extends AppCompatActivity {
    private TimelineAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Events", Dao.getInstance(getBaseContext()).getEvents().toString());
        adapter = new TimelineAdapter(Dao.getInstance(getBaseContext()).getEvents(), this);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
