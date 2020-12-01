package francois.fr.applipromob.objetsJeux;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Puzzle {
    String name;
    Drawable imgResult;
    ArrayList<PiecePuzzle> listPiece;

    public Puzzle(String name, Drawable imgResult, ArrayList<PiecePuzzle> lp) {
        this.name = name;
        this.imgResult = imgResult;
        this.listPiece = lp;
    }

    public void melangePieces() {
        List<Integer> values = new ArrayList();
        for(int i=0; i<30;i++) {
            values.add(i);
        }
        System.out.println("Longueur values : " +values.size());
        for (int i = 0; i < listPiece.size(); i++) {
            Random r = new Random();
            int nbRand = r.nextInt(values.size());
            listPiece.get(i).setPosAleatoire(values.get(nbRand));
            values.remove(values.get(nbRand));
            System.out.println(values.toString());
        }
    }

    public ArrayList<PiecePuzzle> getLp() {
        return listPiece;
    }

    public boolean termine() {
        for(int i=0; i<listPiece.size();i++) {
            if(listPiece.get(i).isVisible()) return false;
        }
        return true;
    }
}
