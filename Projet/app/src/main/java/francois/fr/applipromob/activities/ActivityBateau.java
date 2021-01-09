
package francois.fr.applipromob.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import francois.fr.applipromob.R;
import francois.fr.applipromob.gameview.GameViewBateau;
import francois.fr.applipromob.gameview.GameViewCourse;
import francois.fr.applipromob.sensors.Accelerometer;

public class ActivityBateau extends AppCompatActivity{
    private GameViewBateau gameViewB;
    private TextView txtTimer;
    private int time; // temps en sec
    private float prevX,prevY,prevZ;


    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bateau);

        //Initialisation des valeurs de l'accelerometre
        prevX = 0;
        prevY = 0;
        prevZ = 0;

        gameViewB = new GameViewBateau(getApplicationContext());
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
                setContentView(gameViewB);
            }
        }.start();

        //Gestion de l'accelerometre
        /*accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                int mouv = 2;
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
        });*/


    }

    @Override
    protected void onResume(){
        super.onResume();
        //accelerometer.register();
    }

    @Override
    protected void onPause(){
        super.onPause();
        //accelerometer.unregister();
    }

}