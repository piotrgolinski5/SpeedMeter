package pl.golinski.piotr.speedmeter.utils;

import android.location.Location;

public class PGMath {

    public static Double distance(Location one, Location two) {
        int R = 6371000;
        Double dLat = toRad(two.getLatitude() - one.getLatitude());
        Double dLon = toRad(two.getLongitude() - one.getLongitude());
        Double lat1 = toRad(one.getLatitude());
        Double lat2 = toRad(two.getLatitude());
        Double a = java.lang.Math.sin(dLat / 2) * java.lang.Math.sin(dLat / 2)
                + java.lang.Math.sin(dLon / 2) * java.lang.Math.sin(dLon / 2) * java.lang.Math.cos(lat1) * java.lang.Math.cos(lat2);
        Double c = 2 * java.lang.Math.atan2(java.lang.Math.sqrt(a), java.lang.Math.sqrt(1 - a));
        Double d = R * c;
        return d;
    }

    private static double toRad(Double d) {
        return d * java.lang.Math.PI / 180;
    }
}
