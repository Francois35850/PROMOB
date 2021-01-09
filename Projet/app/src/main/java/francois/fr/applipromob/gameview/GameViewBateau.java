package francois.fr.applipromob.gameview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import francois.fr.applipromob.R;
import francois.fr.applipromob.objetsJeux.Runner;
import francois.fr.applipromob.objetsJeux.Wind;
import francois.fr.applipromob.objetsJeux.finishLine;
import francois.fr.applipromob.thread.GameLoopBateau;
import francois.fr.applipromob.thread.GameLoopCourse;

public class GameViewBateau extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopBateau gameLoopThread;
    private int score;
    private long startTime;
    private int tpstotal;


    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    // création de la surface de dessin
    public GameViewBateau(Context context) {
        super(context);
        score = 0;
        tpstotal = 0;
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopBateau(this, this.getContext());

    }

    // Fonction qui "dessine" un écran de jeu
    @SuppressLint("ResourceAsColor")
    public void doDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }

        int w = canvas.getWidth();
        int h = canvas.getHeight();

        // on efface l'écran, en blanc
        canvas.drawColor(Color.WHITE);

        Paint textScore = new Paint();
        textScore.setColor(R.color.bleu_fonce);
        textScore.setTextSize(50);

    }


    // Fonction appelée par la boucle principale (gameLoopThread)
    // On gère ici le déplacement des objets
    public void update() {
        if (false) {
            gameLoopThread.setRunning(false);
        }
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée immédiatement après la création de l'objet SurfaceView
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // création du processus GameLoopThread si cela n'est pas fait
        if (gameLoopThread.getState() == Thread.State.TERMINATED) {
            gameLoopThread = new GameLoopBateau(this, this.getContext());
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();

    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée juste avant que l'objet ne soit détruit.
    // on tente ici de stopper le processus de gameLoopThread
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
    }



}

