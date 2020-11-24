package francois.fr.applipromob.objetsJeux;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    String name;
    Drawable imgResult;
    ArrayList<PiecePuzzle> listPiece;

    public Puzzle(String name, Drawable imgResult, ArrayList<PiecePuzzle> lp) {
        this.name = name;
        this.imgResult = imgResult;
        this.listPiece = lp;
    }

    public ArrayList<PiecePuzzle> getLp() {
        return listPiece;
    }
}
