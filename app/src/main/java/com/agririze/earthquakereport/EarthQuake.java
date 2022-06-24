package com.agririze.earthquakereport;

public class EarthQuake {
    /** Magnitude of the earthquake */
    private String mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** Date of the earthquake */
    private long mMilliSeconds;

    public EarthQuake(String mMagnitude, String mLocation, long mMilliSeconds) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mMilliSeconds = mMilliSeconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getMilliSeconds() {
        return mMilliSeconds;
    }
}
