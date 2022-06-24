package com.agririze.earthquakereport;

public class EarthQuake {
    /** Magnitude of the earthquake */
    private double mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** Date of the earthquake */
    private long mMilliSeconds;

    private String mUrl;

    public EarthQuake(double mMagnitude, String mLocation, long mMilliSeconds,String mUrl) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mMilliSeconds = mMilliSeconds;
        this.mUrl = mUrl;
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

    public String getUrl() {
        return mUrl;
    }
}
