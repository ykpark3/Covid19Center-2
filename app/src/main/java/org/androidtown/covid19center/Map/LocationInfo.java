package org.androidtown.covid19center.Map;

import android.location.Location;

public class LocationInfo {

    private double longitude;
    private double latitude;
    private double longitudeSecond;
    private double latitudeSecond;

    public LocationInfo(double longitude, double latitude, double longitudeSecond, double latitudeSecond){
        this.longitude = longitude;
        this.latitude = latitude;
        this.latitudeSecond = latitudeSecond;
        this.longitudeSecond = longitudeSecond;
    };

    public double getDistance(){
        double distance;

        Location locationA = new Location("point A");
        locationA.setLatitude(longitude);
        locationA.setLongitude(latitude);

        Location locationB = new Location("point B");
        locationB.setLatitude(longitudeSecond);
        locationB.setLongitude(latitudeSecond);

        distance = locationA.distanceTo(locationB);

        return distance;

    }
}

