package com.jacobgb24.ldstimeline.Model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacob_000 on 2/17/2018.
 */

public class Dao {
    private static Dao dao;
    private static String file = "events.json";
    private List<Event> events;
    public static Dao getInstance(Context context) {
        if(dao == null)
            dao = new Dao(context);
        return dao;
    }

    private Dao(Context context) {
        try {
            InputStream is = context.getAssets().open(file);

            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            Type typeToken = new TypeToken<List<Event>>() {
            }.getType();
            events = new Gson().fromJson(new String(buffer, "UTF-8"), typeToken);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Event> getEvents() {
        return events;
    }
}
