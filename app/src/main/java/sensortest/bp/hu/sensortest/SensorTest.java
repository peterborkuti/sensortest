package sensortest.bp.hu.sensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by peter on 11/8/15.
 */
public class SensorTest implements SensorEventListener {
    private static final String TAG = SensorTest.class.getSimpleName();

    private boolean ON = false;

    private final Context mContext;
    private boolean listenersSet = false;

    public SensorTest(Context mContext) {
        Log.d(TAG, "started");
        this.mContext = mContext;
    }

    public void listSensors() {
        SensorManager mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s: deviceSensors) {
            Log.d(TAG, "Sensor:" + s.getName() + " - " + s);
        }
    }


    private Sensor getDefaultAccelerometer() {
        SensorManager mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);

        Sensor s = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (s != null) {
            Log.d(TAG, "default accelerometer:" + s);
        }
        else {
            Log.d(TAG, "NO default accelerometer");
        }

        return s;
    }

    public void setListeners() {
        if (!listenersSet) {
            Sensor s = getDefaultAccelerometer();
            if (s != null && ON) {
                SensorManager mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);

                mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);

                listenersSet = true;
            }
        }
    }

    public void unsetListeners() {
        if (listenersSet) {
            SensorManager mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
            mSensorManager.unregisterListener(this);
            listenersSet = false;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "Sensor values:" + Arrays.toString(event.values));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "Accuracy changed:" + accuracy);
    }
}
