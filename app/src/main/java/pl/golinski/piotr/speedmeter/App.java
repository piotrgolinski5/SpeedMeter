package pl.golinski.piotr.speedmeter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import pl.golinski.piotr.speedmeter.interfaces.AppCallback;
import pl.golinski.piotr.speedmeter.interfaces.LocationCallback;
import pl.golinski.piotr.speedmeter.location.PGLocationManager;
import pl.golinski.piotr.speedmeter.model.PGModel;
import pl.golinski.piotr.speedmeter.model.SpeedStats;

public class App extends Application implements AppCallback{
    public static final String SPEEDMETER = "SPEEDMETER";
    public static final String STATS = "STATS";
    private PGLocationManager mLocationManager;
    public static SpeedStats mSpeedStats;

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationManager = new PGLocationManager(this);
        getStats();
    }

    @Override
    public void setGPSDisabled() {
        mLocationManager.setGPSDisabled();
    }

    @Override
    public void setGPSEnabled() {
        mLocationManager.setGPSEnabled();
    }

    @Override
    public void setLocationCallback(LocationCallback locationCallback) {
        mLocationManager.setLocationCallback(locationCallback);
    }

    public void saveStats(){
        SharedPreferences.Editor editor = this.getSharedPreferences(SPEEDMETER, Context.MODE_PRIVATE).edit();
        editor.putString(STATS, mSpeedStats.getObjectAsJSON());
        editor.commit();
    }

    private void getStats(){
        SharedPreferences sharedPreferences = getSharedPreferences(SPEEDMETER, Context.MODE_PRIVATE);
        String stats = sharedPreferences.getString(STATS, null);
        if(stats == null){
            mSpeedStats = new SpeedStats();
        }else{
            Gson gson = new Gson();
            mSpeedStats = gson.fromJson(stats, SpeedStats.class);
        }

    }
}
