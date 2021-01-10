package francois.fr.applipromob.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import francois.fr.applipromob.gameview.GameViewCourse;
import francois.fr.applipromob.thread.GameLoopCourse;


public class Accelerometer {
    public interface Listener {
        void onTranslation(float tx, float ty, float tz);
    }
    private Listener listener;

    public void setListener(Listener l){
        listener = l;
    }
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;


    public Accelerometer(Context c){
        sensorManager = (SensorManager) c.getSystemService(c.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (listener!=null){
                    listener.onTranslation(event.values[0],event.values[1],event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }

    public void register(){
        sensorManager.registerListener(sensorEventListener,sensor,sensorManager.SENSOR_DELAY_FASTEST );
    }
    public void unregister(){
        sensorManager.unregisterListener(sensorEventListener);
    }


}
