package francois.fr.applipromob.objetsJeux;

import android.graphics.drawable.Drawable;

public class PiecePuzzle {
    Drawable img;
    int pos; // Position dans le puzzle (de 1 à 30)
    int posAleatoire;
    boolean visible; // Visible ou non

    public PiecePuzzle(Drawable img, int pos) {
        this.img = img;
        this.pos = pos;
        this.visible = true;
    }

    public Drawable getImg() {
        return img;
    }

    public int getPos() {
        return pos;
    }

    public int getPosAleatoire() {
        return posAleatoire;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPosAleatoire(int posAleatoire) {
        this.posAleatoire = posAleatoire;
    }
}
