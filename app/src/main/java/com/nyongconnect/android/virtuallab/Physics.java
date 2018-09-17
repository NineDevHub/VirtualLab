package com.nyongconnect.android.virtuallab;

import java.io.Serializable;

/**
 * Created by enyason on 9/9/18.
 */

public class Physics implements Serializable {


    String code,title;
    double duration;
    boolean isInitialized;


    public Physics(String code, String title, double duration, boolean isInitialized) {
        this.code = code;
        this.title = title;
        this.duration = duration;
        this.isInitialized = isInitialized;
    }


    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public boolean isInitialized() {
        return isInitialized;
    }
}
