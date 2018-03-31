package com.jacobgb24.ldstimeline.views;

/**
 * Created by jacob_000 on 3/31/2018.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jacobgb24.ldstimeline.R;
import com.jacobgb24.ldstimeline.model.Dao;
import com.jacobgb24.ldstimeline.model.Event;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker;

public class MapFragment extends android.support.v4.app.Fragment {

    private GoogleMap map;
    private TextView eventName;
    private TextView eventDate;
    private TextView eventLocation;
    private LinearLayout eventLayout;
    private Event event;
    private MapView mapView;
    private LatLngBounds.Builder boundsBuilder;
    private List<Marker> markers;
    private boolean mapLoaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = v.findViewById(R.id.mapView);
        markers = new ArrayList<>();
        eventLayout = (LinearLayout) v.findViewById(R.id.map_event);
        eventName = (TextView) v.findViewById(R.id.map_event_name);
        eventDate = (TextView) v.findViewById(R.id.map_event_date);
        eventLocation = (TextView) v.findViewById(R.id.map_event_location);
        eventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (event != null) {
                    Intent intent = new Intent(getActivity(), DetailedActivity.class);
                    intent.putExtra("EVENT", event);
                    getActivity().startActivity(intent);
                }
            }
        });

        mapView.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.getUiSettings().setMapToolbarEnabled(false);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));
                loadEvents();
                setMarkerListener();
                if (getArguments() != null) {
                    goToEvent(getArguments().getString("EVENT_ID"));
                }
                else {
                    LatLngBounds bounds = boundsBuilder.build();
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 50);
                    map.moveCamera(cu);
                }
                mapLoaded = true;
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

        if(event == null) {
            eventLayout.setVisibility(View.GONE);
        }
        else {
            eventLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    private void loadEvents() {
        boundsBuilder = new LatLngBounds.Builder();

        List<Event> events = Dao.getInstance(getContext()).getEvents();
        for (int i = 0; i < events.size(); i++) {
            LatLng loc = new LatLng(events.get(i).getLatitude(), events.get(i).getLongitude());
            MarkerOptions options =
                    new MarkerOptions().position(loc).title(events.get(i).getLocation());
            Marker marker = map.addMarker(options);
            marker.setTag(events.get(i));
            markers.add(marker);
            boundsBuilder.include(loc);
        }
        for(int i = 0; i < events.size() - 1; i++) {
            LatLng loc1 = new LatLng(events.get(i).getLatitude(), events.get(i).getLongitude());
            LatLng loc2 = new LatLng(events.get(i + 1).getLatitude(), events.get(i + 1).getLongitude());
            drawLine(loc1, loc2, R.color.lines, 5);
        }
    }

    private void setEventDetails(Event event) {
        eventLayout.setVisibility(View.VISIBLE);
        eventName.setText(event.getName());
        eventDate.setText(event.getDate());
        eventLocation.setText(event.getLocation());
    }

    private void setMarkerListener() {
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                event = (Event) marker.getTag();
                setEventDetails(event);
                return false;
            }
        });
    }

    private void drawLine(LatLng point1, LatLng point2, int color, int width) {
        PolylineOptions options = new PolylineOptions().add(point1, point2).color(color).width(width);
        Polyline line = map.addPolyline(options);
    }


    private void goToEvent(String name) {
        event = Dao.getInstance(getContext()).getEventByName(name);
        setEventDetails(event);
        LatLng eventLoc = new LatLng(event.getLatitude(), event.getLongitude());
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(eventLoc, 7);
        map.moveCamera(update);
        for(Marker marker: markers) {
            if(((Event)marker.getTag()).getName().equals(name)) {
                marker.showInfoWindow();
                break;
            }
        }
    }

}

