package francois.fr.applipromob.ecransFins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import francois.fr.applipromob.MainActivity;
import francois.fr.applipromob.PlaySound;
import francois.fr.applipromob.R;

public class FinJP extends AppCompatActivity {

    int indice;

    int prix,tentatives;

    Button quitter;
    TextView resultJP;
    TextView infosJP;

    PlaySound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin_jp);

        indice = getIntent().getIntExtra("indice", -1);

        sound = new PlaySound(R.raw.victory,this);

        quitter = findViewById(R.id.quitter);
        resultJP = findViewById(R.id.resultJP);
        infosJP = findViewById(R.id.infosJP);

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

        prix = getIntent().getIntExtra("justePrix", 0);
        tentatives = getIntent().getIntExtra("try",0);
        resultJP.setText(String.valueOf(prix).substring(0,2) + " " + String.valueOf(prix).substring(2) + " €");
        if(tentatives != 1) {
            infosJP.setText("Vous avez trouvé le juste prix en " + tentatives + " tentatives");
        } else infosJP.setText("Vous avez trouvé le juste prix en 1 tentative");
    }
}