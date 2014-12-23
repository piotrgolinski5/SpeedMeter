package pl.golinski.piotr.speedmeter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pl.golinski.piotr.speedmeter.activity.PGActivity;
import pl.golinski.piotr.speedmeter.interfaces.LocationCallback;


public class MainActivity extends PGActivity implements LocationCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    @Override
    public void onSpeedChanged(int speed) {
        mSpeed.setText(addObjects(speed, "mph"));
        mInfo.setText(addObjects("last speed: ", App.mSpeedStats.mLastSpeed,"\n\nmax speed: ", App.mSpeedStats.mMaxSpeed,"\n\nlast 100: ", App.mSpeedStats.mLastTimeTo100,"\n\nbest 100: ", App.mSpeedStats.mBestTimeTo100));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getApp().setLocationCallback(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getApp().setLocationCallback(null);
    }

    /*
     *Views
     */
    private TextView mSpeed;
    private TextView mInfo;

    private void setViews() {
        mSpeed = (TextView) findViewById(R.id.activity_main_tvSpeed);
        mInfo = (TextView) findViewById(R.id.activity_main_tvInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
