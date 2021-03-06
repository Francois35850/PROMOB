package francois.fr.applipromob.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import francois.fr.applipromob.R;
import francois.fr.applipromob.gameview.GameViewCible;

public class ActivityCible extends AppCompatActivity {
    private GameViewCible gameViewC;
    TextView txtTimer;
    int time; // temps en sec

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cible);

        final int indice = getIntent().getIntExtra("indice", -1);

        time = 5; // temps du décompte en secondes
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
                gameViewC = new GameViewCible(getApplicationContext(),indice);
                setContentView(gameViewC);
            }
        }.start();
    }
}