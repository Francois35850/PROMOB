package francois.fr.applipromob.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import francois.fr.applipromob.R;
import francois.fr.applipromob.gameview.GameViewJP;

public class ActivityJustePrix extends AppCompatActivity {
    TextView txtTimer;
    int time; // temps en sec
    View v;

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juste_prix);
        v = findViewById(R.id.imageView1);
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
                // Lancement activité jeu
                Intent jeu = new Intent(getApplicationContext(), GameViewJP.class);
                startActivity(jeu);
            }
        }.start();
    }
}