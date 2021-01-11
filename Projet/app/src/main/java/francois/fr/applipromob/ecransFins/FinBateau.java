package francois.fr.applipromob.ecransFins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import francois.fr.applipromob.MainActivity;
import francois.fr.applipromob.PlaySound;
import francois.fr.applipromob.R;

public class FinBateau extends AppCompatActivity {

    int indice;
    String nameBateau;
    int tps;
    int score;

    Button quitter;
    TextView infosBateau;

    PlaySound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin_bateau);

        indice = getIntent().getIntExtra("indice", -1);

        sound = new PlaySound(R.raw.victory, this);

        quitter = findViewById(R.id.quitter);
        infosBateau = findViewById(R.id.infosBateau);

        if (indice != -1 && indice != 3) {
            quitter.setText("Jeu suivant");
        }

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indice != -1 && indice != 3) {
                    int rand = (int) (Math.random() * 6);
                    if (rand == 6) rand = 5;
                    while (MainActivity.jeuxJoues.contains(rand)) {
                        rand = (int) (Math.random() * 6);
                        if (rand == 6) rand = 5;
                    }
                    MainActivity.addJeuxJoues(rand);
                    MainActivity.lancerAct(getApplication(), rand, indice + 1);
                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });

        nameBateau = getIntent().getStringExtra("nom");
        tps = getIntent().getIntExtra("temps", 0);
        score = getIntent().getIntExtra("Score", 0);
        infosBateau.setText("Vous avez survécu " + tps + " secondes et votre score est de " + score + " points");
    }
}