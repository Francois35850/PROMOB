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
    float x,y,z;
    float newX,newY,newZ;

    public AccelerometerCourse(Context c, GameViewCourse g){
        context = c;
        GameView = g;
        //Appel de tout les sensors
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //Accès à notre accéléromètre
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Initialisation values
        newX = 0; newY = 0; newZ = 0;
    }

    public boolean isValuesChanged(){
        return x!=newX||y!=newY||z!=newZ;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor==accelerometre) {
            x = newX;
            y = newY;
            z = newZ;
            newX = event.values[0];
            newY = event.values[1];
            newZ = event.values[2];

            GameView.getRunner().setX(GameView.getRunner().getX() + 50);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public Sensor getAccelerometre() {
        return accelerometre;
    }

    public void setAccelerometre(Sensor accelerometre) {
        this.accelerometre = accelerometre;
    }

    public String getVal(){
        return newX + " " + newY + " " + newZ;
    }
}
