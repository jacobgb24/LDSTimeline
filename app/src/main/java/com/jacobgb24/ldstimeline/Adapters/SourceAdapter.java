package com.jacobgb24.ldstimeline.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jacobgb24.ldstimeline.Model.Event;
import com.jacobgb24.ldstimeline.Model.Pair;
import com.jacobgb24.ldstimeline.R;

import java.util.List;
import java.util.Map;

/**
 * Created by jacob_000 on 3/17/2018.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder>{

    private List<Pair> sources;
    private final LayoutInflater inflater;
    private Activity activity;


    public SourceAdapter(Activity activity, List<Pair> sources) {
        this.sources = sources;
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.list_item_source, viewGroup, false);
        return new ViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int pos) {
        holder.bind(sources.get(pos));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView source;
        private Activity activity;

        public ViewHolder(View v, Activity activity) {
            super(v);
            this.activity = activity;
            source = (TextView) v.findViewById(R.id.list_source);


        }
        void bind(final Pair sourcePair) {
            source.setText(sourcePair.getKey());
            source.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sourcePair.getValue()));
                    activity.startActivity(browserIntent);
                }
            });
        }

    }

}
