package com.agririze.earthquakereport;

public class EarthQuake {
    /** Magnitude of the earthquake */
    private double mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** Date of the earthquake */
    private long mMilliSeconds;

    public EarthQuake(double mMagnitude, String mLocation, long mMilliSeconds) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mMilliSeconds = mMilliSeconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getMilliSeconds() {
        return mMilliSeconds;
    }
}
