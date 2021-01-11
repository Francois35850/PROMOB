
package francois.fr.applipromob.activities;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.widget.TextView;

        import java.util.List;

        import francois.fr.applipromob.R;
        import francois.fr.applipromob.gameview.GameViewCourse;
        import francois.fr.applipromob.sensors.Accelerometer;

public class ActivityCourse extends AppCompatActivity{
    private GameViewCourse gameViewC;
    private TextView txtTimer;
    private int time; // temps en sec
    private Accelerometer accelerometer;
    private float prevX,prevY,prevZ;


    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athle);

        int indice = getIntent().getIntExtra("indice", -1);

        //Initialisation des valeurs de l'accelerometre
        prevX = 0;
        prevY = 0;
        prevZ = 0;

        gameViewC = new GameViewCourse(getApplicationContext(),indice);
        time = 2; // temps du décompte en secondes
        txtTimer = findViewById(R.id.txtTimer);
        new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long l) {
                txtTimer.setText("0:" + checkDigit(time));
                time--;
            }

            @Override
            public void onFinish() {
                txtTimer.setText("GO !!");
                gameViewC.setTimGV(System.currentTimeMillis());
                setContentView(gameViewC);
            }
        }.start();

        //Gestion de l'accelerometre
        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                int mouv = 8;
                if (prevX > tx + mouv || prevX < tx - mouv || prevY > ty + mouv || prevY < ty - mouv || prevZ > tz + mouv || prevZ < tz - mouv ) {
                    int step = 1*gameViewC.getRunner().getScreenW()/100;
                    if (!gameViewC.getWind().isActive()) {
                        gameViewC.getRunner().setX(gameViewC.getRunner().getX()+step);
                    }
                    else if (gameViewC.getRunner().getX()>step) gameViewC.getRunner().setX(gameViewC.getRunner().getX()-(step));
                    prevX = tx;
                    prevY = ty;
                    prevZ = tz;
                }
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();
        accelerometer.register();
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerometer.unregister();
    }

}