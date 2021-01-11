package francois.fr.applipromob;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import francois.fr.applipromob.activities.ActivityBateau;
import francois.fr.applipromob.activities.ActivityCible;
import francois.fr.applipromob.activities.ActivityCourse;
import francois.fr.applipromob.activities.ActivityJustePrix;
import francois.fr.applipromob.activities.ActivityPuzzle;
import francois.fr.applipromob.activities.ActivityQuizz;

public class MainActivity extends AppCompatActivity {

    public static List<Jeu> miniJeux = new ArrayList<Jeu>();
    public static List<Integer> jeuxJoues = new ArrayList<>();

    public void addMiniJeu(Jeu j) {
        miniJeux.add(j);
    }

    public static void addJeuxJoues(int i) {
        jeuxJoues.add(i);
    }

    public static void clearJJ() {
        jeuxJoues.clear();
    }

    public static void lancerAct(Context ctx, int numJeu, int indice) {
        Intent act = null;
        switch (numJeu) {
            case 0:
                act = new Intent(ctx, ActivityCourse.class);
                break;
            case 1:
                act = new Intent(ctx, ActivityCible.class);
                break;
            case 2:
                act = new Intent(ctx, ActivityBateau.class);
                break;
            case 3:
                act = new Intent(ctx, ActivityPuzzle.class);
                break;
            case 4:
                act = new Intent(ctx, ActivityJustePrix.class);
                break;
            case 5:
                act = new Intent(ctx, ActivityQuizz.class);
                break;
            default:
        }
        act.putExtra("indice", indice);
        act.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(act);
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
            }
        });
    }

    public void permission() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CHANGE_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
        };
        ActivityCompat.requestPermissions(this, permissions, PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miniJeux.clear();

        permission();

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
        addMiniJeu(new Jeu("Quizz", 4, Jeu.VictoryType.POINTS, R.drawable.jeu_quizz, R.drawable.jeu_quizz));
    }
}
