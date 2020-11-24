package francois.fr.applipromob.objetsJeux;

import android.graphics.drawable.Drawable;

public class PiecePuzzle {
    Drawable img;
    int pos; // Position dans le puzzle (de 1 à 30)

    public PiecePuzzle(Drawable img, int pos) {
        this.img = img;
        this.pos = pos;
    }

    public Drawable getImg() {
        return img;
    }

    public int getPos() {
        return pos;
    }
}
