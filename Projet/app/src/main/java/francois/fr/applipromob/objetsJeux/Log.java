package francois.fr.applipromob.objetsJeux;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Random;

import francois.fr.applipromob.R;

public class Log {
    private BitmapDrawable img=null; // image
    private int x,y; // coordonnées x,y en pixel
    private int logW, logH; // largeur et hauteur en pixels
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels

    private final Context mContext;

    // Constructeur de l'objet "Log"
    public Log(final Context c)
    {
        x= 0; y=0; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "Log" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int w, final int h)
    {
        int ressource = R.drawable.log;
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
        double randomX = Math.random()*wEcran;
        setX((int)randomX);
        setY(-hEcran*5/100);
        // on définit (au choix) la taille de la cible à 1/5ème de la largeur de l'écran
        logW=wScreen/5;
        logH=wScreen/10;
        img = setImage(mContext,logW,logH);
    }


    // définit la coordonnée X
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y
    public int getY() {
        return y;
    }

    // retourne la largeur du log en pixel
    public int getLogW() {
        return logW;
    }

    // retourne la hauteur du log en pixel
    public int getLogH() {
        return logH;
    }

    // retourne la hauteur de l ecran en pixel
    public int getScreenH() {
        return hEcran;
    }

    // retourne la largeur de l ecran en pixel
    public int getScreenW() {
        return wEcran;
    }

    // on dessine le log, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }
}
