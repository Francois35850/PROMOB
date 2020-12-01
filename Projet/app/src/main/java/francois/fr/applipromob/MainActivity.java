package francois.fr.applipromob;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Jeu> miniJeux = new ArrayList<Jeu>();

    public void addMiniJeu(Jeu j) {
        miniJeux.add(j);
    }

    // Fonction qui gère les clics sur les boutons
    void clickButton(Button btn, final int numeroBtn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeroBtn == 1) {
                    Intent activity = new Intent(getApplicationContext(), Solo.class);
                    startActivity(activity);
                    finish();
                }
                if (numeroBtn == 2) {
                    Intent activity = new Intent(getApplicationContext(), Multi.class);
                    startActivity(activity);
                    finish();
                }
                if (numeroBtn == 3) {
                    Intent activity = new Intent(getApplicationContext(), Stats.class);
                    startActivity(activity);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miniJeux.clear();


        // Boutons qui recherche les joueurs connectés
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO : Affiche la liste des joueurs connectés", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Récupération des boutons + appel fonction qui les gèrent
        Button bt_solo = findViewById(R.id.boutonSolo);
        Button bt_multi = findViewById(R.id.boutonMulti);
        Button bt_stats = findViewById(R.id.boutonStats);
        clickButton(bt_solo, 1);
        clickButton(bt_multi, 2);
        clickButton(bt_stats, 3);
        addMiniJeu(new Jeu("Athlétisme", 4, Jeu.VictoryType.TEMPS, R.drawable.jeu_athletisme, R.drawable.jeu_athletisme));
        addMiniJeu(new Jeu("Cibles", 4, Jeu.VictoryType.POINTS, R.drawable.jeu_cibles, R.drawable.jeu_cibles));
        addMiniJeu(new Jeu("Bateau", 4, Jeu.VictoryType.TEMPS, R.drawable.jeu_bateau, R.drawable.jeu_bateau));
        addMiniJeu(new Jeu("Puzzle", 4, Jeu.VictoryType.TEMPS, R.drawable.jeu_puzzle, R.drawable.jeu_puzzle));
        addMiniJeu(new Jeu("Juste Prix", 4, Jeu.VictoryType.POINTS, R.drawable.jeu_juste_prix, R.drawable.jeu_juste_prix));
    }
}
