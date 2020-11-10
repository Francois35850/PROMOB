package francois.fr.applipromob.jeux;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import francois.fr.applipromob.R;

public class Cibles extends AppCompatActivity {
    String nom;
    int nbJoueurs;
    VictoryType vt;
    Integer image;
    public Cibles(String name, int nbJoueurs, VictoryType vt, Integer image) {
        this.nom = name;
        this.nbJoueurs = nbJoueurs;
        this.vt = vt;
        this.image = image;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu_cibles);
    }

    public static enum VictoryType {
        POINTS, TEMPS;
    }

}
