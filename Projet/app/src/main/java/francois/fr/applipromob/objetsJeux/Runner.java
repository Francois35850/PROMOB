package francois.fr.applipromob.objetsJeux;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Random;

import francois.fr.applipromob.R;

public class Runner {

    private BitmapDrawable img = null; // image
    private int x,y; // coordonnées x,y en pixel
    private int runnerW, runnerH; // largeur et hauteur de l'image en pixels
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private int pngNumber;

    private final Context mContext;

    // Constructeur de l'objet "Runner"
    public Runner(final Context c)
    {
        x= 0; y=0; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "Runner" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int w, final int h)
    {
        int ressource;
        int[] ressources =
                {R.drawable.runner_01, R.drawable.runner_02, R.drawable.runner_03,R.drawable.runner_04,R.drawable.runner_05,R.drawable.runner_06,R.drawable.runner_07,R.drawable.runner_08,R.drawable.runner_09,R.drawable.runner_10};
        if (img==null){
            ressource = ressources[0];
            pngNumber = 0;
        }
        else if (pngNumber<9){
            pngNumber = pngNumber + 1;
            ressource = ressources[pngNumber];
        }
        else {
            ressource = ressources[9];
            pngNumber = -1;
        }
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
        setY(hEcran*40/100);
        // on définit (au choix) la taille du runner à 1/5ème de la largeur de l'écran
        runnerW=wEcran/5;
        runnerH=hEcran/5;
        img = setImage(mContext,runnerW,runnerH);
    }

    // définit la coordonnée X du runner
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du runner
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du runner
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du runner
    public int getY() {
        return y;
    }

    // retourne la largeur du runner en pixel
    public int getRunnerW() {
        return runnerW;
    }

    // retourne la largeur du runner en pixel
    public int getScreenW() {
        return wEcran;
    }

    // retourne la hauteur du runner en pixel
    public int getRunnerH() {
        return runnerH;
    }

    // on dessine le runner, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }
}