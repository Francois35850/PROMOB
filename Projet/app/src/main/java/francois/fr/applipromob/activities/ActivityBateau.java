
package francois.fr.applipromob.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import francois.fr.applipromob.R;
import francois.fr.applipromob.gameview.GameViewBateau;
import francois.fr.applipromob.sensors.Accelerometer;

public class ActivityBateau extends AppCompatActivity {
    private GameViewBateau gameViewB;
    private TextView txtTimer;
    private int time; // temps en sec
    private float prevX, prevY, prevZ;
    private Accelerometer accelerometer;


    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bateau);

        int indice = getIntent().getIntExtra("indice", -1);

        //Initialisation des valeurs de l'accelerometre
        prevX = 0;
        prevY = 0;
        prevZ = 0;

        gameViewB = new GameViewBateau(getApplicationContext(), indice);
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
                gameViewB.setTimGV(System.currentTimeMillis());
                setContentView(gameViewB);
            }
        }.start();

        //Gestion de l'accelerometre
        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                int mouv = 4;
                if (prevX > tx + mouv || prevX < tx - mouv) {
                    int XBoat = gameViewB.getBateau().getX();
                    int largeurE = gameViewB.getBateau().getScreenW();
                    if (prevX < tx + mouv && XBoat > 0)
                        gameViewB.getBateau().setX(XBoat - (largeurE * 1 / 100));
                    if (prevX > tx + mouv && XBoat < largeurE - gameViewB.getBateau().getBoatW())
                        gameViewB.getBateau().setX(XBoat + (largeurE * 1 / 100));
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
    }

}