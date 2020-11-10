package francois.fr.applipromob.jeux;

import francois.fr.applipromob.Jeu;
import francois.fr.applipromob.R;

public class Bateau extends Jeu {
    public Bateau(String name, int nbJoueurs,VictoryType vt, Integer image) {
        super(name,nbJoueurs,vt,image, R.layout.jeu_bateau);
    }

}