package com.jacobgb24.ldstimeline.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacobgb24.ldstimeline.R;
import com.jacobgb24.ldstimeline.adapters.TimelineAdapter;
import com.jacobgb24.ldstimeline.model.Dao;

/**
 * Created by jacob_000 on 3/31/2018.
 */

public class TimelineFragment extends Fragment {

    private TimelineAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        adapter = new TimelineAdapter(Dao.getInstance(getContext()).getEvents(), getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
