package francois.fr.applipromob.gameview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import francois.fr.applipromob.R;
import francois.fr.applipromob.thread.GameLoopCible;
import francois.fr.applipromob.thread.GameLoopCourse;

public class GameViewCourse extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopCourse gameLoopThread;
    private int score;
    private long startTime;
    private int tpsTotal;
    private int tpsRestant;

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    // création de la surface de dessin
    public GameViewCourse(Context context) {
        super(context);
        score = 0;
        startTime = System.currentTimeMillis();
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopCourse(this, this.getContext());

        // Gestion temps
        tpsTotal = 8000; // 8sec
        tpsRestant = 8;


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

        canvas.drawText("Score : " + String.valueOf(score), (float) (w * 0.1), (float) (h * 0.1), textScore);
        canvas.drawText("0: " + checkDigit(tpsRestant), (float) (w * 0.1), (float) (h * 0.9), textScore);

        // on dessine la cible
        //cible.draw(canvas);
    }


    // Fonction appelée par la boucle principale (gameLoopThread)
    // On gère ici le déplacement des objets
    public void update() {
        if (System.currentTimeMillis() > startTime + tpsTotal) {
            gameLoopThread.setRunning(false);
        }
        tpsRestant = (int) ((startTime + tpsTotal - System.currentTimeMillis()) / 1000) + 1;
        System.out.println(tpsRestant);
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée immédiatement après la création de l'objet SurfaceView
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // création du processus GameLoopThread si cela n'est pas fait
        if (gameLoopThread.getState() == Thread.State.TERMINATED) {
            gameLoopThread = new GameLoopCourse(this, this.getContext());
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

    // Gère les touchés sur l'écran
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();

        switch (event.getAction()) {

            // code exécuté lorsque le doigt touche l'écran.
            case MotionEvent.ACTION_DOWN:
                // si le doigt touche la cible :

                break;
        }

        return true;  // On retourne "true" pour indiquer qu'on a géré l'évènement
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        //cible.resize(w, h); // on définit la taille de la cible selon la taille de l'écran
        //cible.randomLocation();
    }

}

