package francois.fr.applipromob.objetsJeux;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Random;

import francois.fr.applipromob.R;

public class finishLine {

    private BitmapDrawable img = null; // image
    private int x,y; // coordonnées x,y en pixel
    private int fLineW, fLineH; // largeur et hauteur de l'image en pixels
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels

    private final Context mContext;

    // Constructeur de l'objet "finishLine"
    public finishLine(final Context c)
    {

        x= 0; y= 0; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "finishLine" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int w, final int h)
    {
        //A modifier
        int ressource = R.drawable.finish_line;
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    // redimensionnement de l'image selon la largeur/hauteur de l'écran passés en paramètre
    public void resize(int wScreen, int hScreen) {
        // wEcran et hEcran sont la largeur et la hauteur de l'écran en pixel
        wEcran=wScreen;
        hEcran=hScreen;

        //On change la position de la ligne d'arrivée mnt qu'on a les dimensions de l'écran
        setX(wEcran*94/100);
        setY(hEcran*40/100);

        // on définit (au choix) la taille au 1/5ème de la largeur de l'écran
        fLineW=wEcran/15;
        fLineH=hEcran/5;
        img = setImage(mContext,fLineW,fLineH);
    }

    // définit la coordonnée X de fLine
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du fLine
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du fLine
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du fLine
    public int getY() {
        return y;
    }

    // retourne la largeur du fLine en pixel
    public int getfLineW() {
        return fLineW;
    }

    // retourne la hauteur du fLine en pixel
    public int getfLineH() {
        return fLineH;
    }

    // on dessine la fLine, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }
}