package sensortest.bp.hu.sensortest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "SensorTest";
    private SensorTest sensorTest;
    private VideoPlayer videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorTest = new SensorTest(this);
        sensorTest.listSensors();
        sensorTest.setListeners();

        videoPlayer = new VideoPlayer(this);
        videoPlayer.start();
        /*
        TextView text = (TextView)findViewById(R.id.x);
        text.setText("Hello");
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorTest != null) {
            sensorTest.unsetListeners();
        }
        if (videoPlayer != null) {
            videoPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sensorTest != null) {
            sensorTest.setListeners();
        }

        if (videoPlayer != null) {
            videoPlayer.start();
        }

    }
}
