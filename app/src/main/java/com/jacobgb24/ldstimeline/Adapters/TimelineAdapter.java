package com.jacobgb24.ldstimeline.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jacobgb24.ldstimeline.Activities.DetailedActivity;
import com.jacobgb24.ldstimeline.Model.Event;
import com.jacobgb24.ldstimeline.R;

import java.util.List;

/**
 * Created by jacob_000 on 2/17/2018.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
    private List<Event> list;
    private final LayoutInflater inflater;

    public TimelineAdapter(List<Event> list, Activity activity) {
        this.list = list;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.list_item_timeline, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int pos) {
        holder.bind(list.get(pos));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView date;
        private TextView location;
        private LinearLayout layout;

        public ViewHolder(View v) {
            super(v);
            layout = (LinearLayout) v.findViewById(R.id.list_timeline_event);
            name = (TextView) v.findViewById(R.id.list_timeline_name);
            date = (TextView) v.findViewById(R.id.list_timeline_date);
            location = (TextView) v.findViewById(R.id.list_timeline_location);

        }
        void bind(final Event event) {
            name.setText(event.getName());
            date.setText(event.getDate());
            location.setText(event.getLocation());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailedActivity.class);
                    intent.putExtra("EVENT", event);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}