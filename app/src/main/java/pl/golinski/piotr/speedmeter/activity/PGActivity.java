package pl.golinski.piotr.speedmeter.activity;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import pl.golinski.piotr.speedmeter.interfaces.AppCallback;

public class PGActivity extends ActionBarActivity {

    protected AppCallback getApp() {
        return (AppCallback) getApplication();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getApp().setGPSEnabled();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getApp().setGPSDisabled();
        getApp().saveStats();
    }

    public static String addObjects(Object ... objects){
        StringBuilder stringBuilder = new StringBuilder();
        for(Object o : objects){
            stringBuilder.append(o);
        }
        return stringBuilder.toString();
    }
}
