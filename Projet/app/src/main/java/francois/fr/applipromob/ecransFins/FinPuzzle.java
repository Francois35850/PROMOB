package francois.fr.applipromob.ecransFins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import francois.fr.applipromob.MainActivity;
import francois.fr.applipromob.PlaySound;
import francois.fr.applipromob.R;

public class FinPuzzle extends AppCompatActivity {

    String namePuzzle;
    int imgPuzzle;
    String tps;

    Button quitter;
    ImageView resultPuzzle;
    TextView infosPuzzle;

    PlaySound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin_puzzle);

        sound = new PlaySound(R.raw.victory,this);

        quitter = findViewById(R.id.quitter);
        resultPuzzle = findViewById(R.id.resultPuzzle);
        infosPuzzle = findViewById(R.id.infosPuzzle);

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        namePuzzle = getIntent().getStringExtra("nom");
        imgPuzzle = getIntent().getIntExtra("puzzle", 0);
        tps = getIntent().getStringExtra("temps");
        resultPuzzle.setImageDrawable(getResources().getDrawable(imgPuzzle));
        infosPuzzle.setText("Vous avez réussi le puzzle " + namePuzzle + " en " + tps);
    }
}