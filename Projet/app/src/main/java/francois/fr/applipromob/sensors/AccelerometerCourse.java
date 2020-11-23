package francois.fr.applipromob.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import francois.fr.applipromob.gameview.GameViewCourse;
import francois.fr.applipromob.thread.GameLoopCourse;


public class AccelerometerCourse extends Activity implements SensorEventListener {
    private Context context;
    private Sensor accelerometre;
    private GameViewCourse GameView;

    public AccelerometerCourse(Context c, GameViewCourse g){
        context = c;
        GameView = g;
        //Appel de tout les sensors
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //Accès à notre accéléromètre
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        GameView.getRunner().setX(GameView.getRunner().getX()+5);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
