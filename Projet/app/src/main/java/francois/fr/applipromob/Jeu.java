package francois.fr.applipromob;

import android.view.View;
import android.widget.TextView;

public class Jeu {
    String nom;
    int nbJoueurs;
    VictoryType vt;
    Integer image;

    public Jeu(String name, int nbJoueurs, VictoryType vt,Integer image) {
        this.nom = name;
        this.nbJoueurs = nbJoueurs;
        this.vt = vt;
        this.image = image;
    }

    public static enum VictoryType {
        POINTS, TEMPS;
    }
}