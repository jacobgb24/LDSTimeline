package com.jacobgb24.ldstimeline.util;

import com.jacobgb24.ldstimeline.model.Event;

/**
 * Created by jacob_000 on 3/23/2018.
 */

public class StaticMapBuilder {

    private static final int ZOOM = 12;
    private static final String URL = "https://maps.googleapis.com/maps/api/staticmap?center=MY_COORDS&zoom=" + Integer.toString(ZOOM) +  "&scale=2&format=png&maptype=roadmap&style=feature:administrative.land_parcel%7Celement:labels%7Cvisibility:off&style=feature:poi%7Celement:labels.text%7Cvisibility:off&style=feature:poi.attraction%7Celement:geometry%7Cvisibility:off&style=feature:poi.business%7Cvisibility:off&style=feature:poi.medical%7Cvisibility:off&style=feature:poi.school%7Cvisibility:off&style=feature:poi.sports_complex%7Cvisibility:off&style=feature:road%7Cvisibility:off&style=feature:road.local%7Celement:labels%7Cvisibility:off&size=640x360&markers=size:small%7Ccolor:0xff0000%7Clabel:%7CMY_COORDS";

    public static String getStaticMapUrl(Event event) {
        String moddedUrl = URL;
        String coords = Double.toString(event.getLatitude()) + "," + Double.toString(event.getLongitude());
        moddedUrl = moddedUrl.replaceFirst("MY_COORDS", coords);
        moddedUrl = moddedUrl.replaceFirst("MY_COORDS", coords);
        System.out.println(moddedUrl);
        return moddedUrl;
    }
}
