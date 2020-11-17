package francois.fr.applipromob.objetsJeux;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Random;

import francois.fr.applipromob.R;

public class Cible {
    private BitmapDrawable img=null; // image de la cible
    private int x,y; // coordonnées x,y de la cible en pixel
    private int cibleW, cibleH; // largeur et hauteur de la cible en pixels
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels

    private final Context mContext;

    // Constructeur de l'objet "Cible"
    public Cible(final Context c)
    {
        x= 0; y=0; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "Cible" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    // redimensionnement de l'image selon la largeur/hauteur de l'écran passés en paramètre
    public void resize(int wScreen, int hScreen) {
        // wEcran et hEcran sont la largeur et la hauteur de l'écran en pixel
        wEcran=wScreen;
        hEcran=hScreen;

        // on définit (au choix) la taille de la cible à 1/5ème de la largeur de l'écran
        cibleW=wScreen/5;
        cibleH=wScreen/5;
        img = setImage(mContext,R.drawable.cible,cibleW,cibleH);
    }

    public void randomLocation(){
        x = new Random().nextInt(wEcran-cibleW+1);
        y = new Random().nextInt((int) (hEcran*0.8)-cibleH+1);
    }

    // définit la coordonnée X de la cible
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y de la cible
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X de la cible
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y de la cible
    public int getY() {
        return y;
    }

    // retourne la largeur de la cible en pixel
    public int getCibleW() {
        return cibleW;
    }

    // retourne la hauteur de la cible en pixel
    public int getCibleH() {
        return cibleH;
    }

    // on dessine la cible, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }
}
