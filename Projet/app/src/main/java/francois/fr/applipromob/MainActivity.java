package francois.fr.applipromob;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import francois.fr.applipromob.jeux.Athletisme;
import francois.fr.applipromob.jeux.Bateau;
import francois.fr.applipromob.jeux.Cibles;
import francois.fr.applipromob.jeux.JustePrix;
import francois.fr.applipromob.jeux.Puzzle;

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
                }
                if (numeroBtn == 2) {
                    Intent activity = new Intent(getApplicationContext(), Multi.class);
                    startActivity(activity);
                }
                if (numeroBtn == 3) {
                    Intent activity = new Intent(getApplicationContext(), Stats.class);
                    startActivity(activity);
                }
                if (numeroBtn == 4) {
                    Intent activity = new Intent(getApplicationContext(), MiniJeux.class);
                    startActivity(activity);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Button bt_minijeux = findViewById(R.id.boutonMiniJeux);
        clickButton(bt_solo, 1);
        clickButton(bt_multi, 2);
        clickButton(bt_stats, 3);
        clickButton(bt_minijeux, 4);
        addMiniJeu(new Athletisme("Athlétisme",4, Jeu.VictoryType.TEMPS,R.drawable.jeu_athletisme));
        addMiniJeu(new Cibles("Cibles",4, Jeu.VictoryType.POINTS,R.drawable.jeu_cibles));
        addMiniJeu(new Bateau("Bateau",4, Jeu.VictoryType.TEMPS,R.drawable.jeu_bateau));
        addMiniJeu(new Puzzle("Puzzle",4, Jeu.VictoryType.TEMPS,R.drawable.jeu_puzzle));
        addMiniJeu(new JustePrix("Juste Prix",4, Jeu.VictoryType.POINTS,R.drawable.jeu_juste_prix));
    }
}
