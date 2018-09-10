package com.nyongconnect.android.virtuallab.adapters;

public class PendulumResult {
    int numberOfOscillation;
    String time;
    int length;
    public PendulumResult(int numberOfOscillation, String time, int length){
        this.numberOfOscillation = numberOfOscillation;
        this.time = time;
        this.length = length;
    }

    public int getNumberOfOscillation() {
        return numberOfOscillation;
    }

    public String getTime() {
        return time;
    }

    public int getLength() {
        return length;
    }
}
