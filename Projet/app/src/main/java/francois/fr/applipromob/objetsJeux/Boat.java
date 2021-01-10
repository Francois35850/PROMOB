package francois.fr.applipromob.objetsJeux;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import francois.fr.applipromob.R;

public class Boat {

    private BitmapDrawable img = null; // image
    private int x,y; // coordonnées x,y en pixel
    private int boatW, boatH; // largeur et hauteur de l'image en pixels
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels

    private final Context mContext;

    // Constructeur de l'objet "Boat"
    public Boat(final Context c)
    {
        x= 0; y=0; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "Boat" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int w, final int h)
    {
        int ressource = R.drawable.boat;
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    // redimensionnement de l'image selon la largeur/hauteur de l'écran passés en paramètre
    public void resize(int wScreen, int hScreen) {
        // wEcran et hEcran sont la largeur et la hauteur de l'écran en pixel
        wEcran=wScreen;
        hEcran=hScreen;

        //Position
        setX(wEcran*50/100);
        setY(hEcran*80/100);
        // on définit la taille du bateau
        boatW=wEcran/8;
        boatH=hEcran/6;
        img = setImage(mContext,boatW,boatH);
    }

    // définit la coordonnée X du boat
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du boat
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du boat
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du boat
    public int getY() {
        return y;
    }

    // retourne la largeur du boat en pixel
    public int getBoatW() {
        return boatW;
    }

    // retourne la largeur du boat en pixel
    public int getScreenW() {
        return wEcran;
    }

    // retourne la hauteur du boat en pixel
    public int getBoatH() {
        return boatH;
    }

    // on dessine le boat, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }
}