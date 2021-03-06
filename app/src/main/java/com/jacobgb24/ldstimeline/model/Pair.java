package com.jacobgb24.ldstimeline.model;

import java.io.Serializable;

/**
 * Created by jacob_000 on 3/17/2018.
 */

public class Pair implements Serializable {
    String key;
    String value;

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
