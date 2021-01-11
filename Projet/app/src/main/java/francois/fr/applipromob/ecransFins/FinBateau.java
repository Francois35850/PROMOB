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

    String nameBateau;
    String tps;
    String score;

    Button quitter;
    ImageView resultBateau;
    TextView infosBateau;

    PlaySound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin_bateau);

        sound = new PlaySound(R.raw.victory,this);

        quitter = findViewById(R.id.quitter);
        resultBateau = findViewById(R.id.resultBateau);
        infosBateau = findViewById(R.id.infosBateau);

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        nameBateau = getIntent().getStringExtra("nom");
        tps = getIntent().getStringExtra("temps");
        score = getIntent().getStringExtra("Score");
        infosBateau.setText("Vous avez survécu " + tps +"s et votre score est de " + score + " points");
    }
}