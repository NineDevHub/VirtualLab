package com.nyongconnect.android.virtuallab;

/**
 * Created by enyason on 9/11/18.
 */

public class Pendulum {

    double length,time,period,tSquare;

    public Pendulum(double length, double time, double period, double tSquare) {
        this.length = length;
        this.time = time;
        this.period = period;
        this.tSquare = tSquare;
    }


    public double getLength() {
        return length;
    }

    public double getTime() {
        return time;
    }

    public double getPeriod() {
        return period;
    }

    public double gettSquare() {
        return tSquare;
    }
}
