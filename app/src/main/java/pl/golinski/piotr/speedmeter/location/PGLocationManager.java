package pl.golinski.piotr.speedmeter.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import pl.golinski.piotr.speedmeter.App;
import pl.golinski.piotr.speedmeter.activity.PGActivity;
import pl.golinski.piotr.speedmeter.interfaces.AppCallback;
import pl.golinski.piotr.speedmeter.interfaces.LocationCallback;
import pl.golinski.piotr.speedmeter.utils.PGMath;


public class PGLocationManager implements AppCallback {
    private final String TAG = "PGLocationManager";
    private LocationManager mLocationManager;
    private LocationCallback mLocationCallback;
    private LocationListener mLocationListener;
    private Location mLastLocation;
    private long mLastLocationTime;
    private long mTimeStart;

    public PGLocationManager(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        setLocationListener();
    }

    private void setLocationListener() {
        mLocationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                locationChanged(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d(TAG, PGActivity.addObjects("onStatusChanged ", provider, " ", status));
            }

            public void onProviderEnabled(String provider) {
                Log.d(TAG, PGActivity.addObjects("onProviderEnabled ", provider));
            }

            public void onProviderDisabled(String provider) {
                Log.d(TAG, PGActivity.addObjects("onProviderEnabled ", provider));
            }

            private void locationChanged(Location location) {
                Log.d(TAG, PGActivity.addObjects("onLocationChanged"));
                if (mLocationCallback != null && mLastLocation != null) {
                    int speed = calculateSpeed(location);
                    mLocationCallback.onSpeedChanged(speed);

                    App.mSpeedStats.setSpeed(speed);

                    calculateTime(speed);
                }

                mLastLocation = location;
                mLastLocationTime = System.currentTimeMillis();
            }
        };
    }

    public void setGPSEnabled() {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                200, mLocationListener);
    }

    public void setGPSDisabled() {
        if (mLocationListener != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }
    }

    public void setLocationCallback(LocationCallback locationCallback) {
        mLocationCallback = locationCallback;
    }


    private void calculateTime(int speed) {
        if (speed == 0) {
            mTimeStart = System.currentTimeMillis();
        } else if (speed >= 100 && speed < 200) {
            App.mSpeedStats.setTimeTo100(System.currentTimeMillis() - mTimeStart);
        } else if (speed == 200) {
            App.mSpeedStats.setTimeTo100(System.currentTimeMillis() - mTimeStart);
        }
    }

    private int calculateSpeed(Location location) {
        double distance = PGMath.distance(location, mLastLocation);
        long timeDiff = System.currentTimeMillis() - mLastLocationTime;
        return (int) (distance / (timeDiff / 1000));
    }

    @Override
    public void saveStats() {
    }
}
