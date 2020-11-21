package francois.fr.applipromob.objetsJeux;

import java.util.List;

public class Puzzle {
    String name;
    int imgResult;
    List<PiecePuzzle> listPiece;

    public Puzzle(String name, int imgResult, List<PiecePuzzle> lp) {
        this.name = name;
        this.imgResult = imgResult;
        this.listPiece = lp;
    }

}
