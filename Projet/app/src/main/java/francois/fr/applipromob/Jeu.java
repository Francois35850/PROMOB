package francois.fr.applipromob;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import francois.fr.applipromob.jeux.Bateau;

public class Jeu extends AppCompatActivity {
    String nom;
    int nbJoueurs;
    VictoryType vt;
    Integer image,res;
    public Jeu(String name, int nbJoueurs, VictoryType vt, Integer image,Integer res) {
        this.nom = name;
        this.nbJoueurs = nbJoueurs;
        this.vt = vt;
        this.image = image;
        this.res = res;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(res);
    }

    public enum VictoryType {
        POINTS,TEMPS;
    }
}
