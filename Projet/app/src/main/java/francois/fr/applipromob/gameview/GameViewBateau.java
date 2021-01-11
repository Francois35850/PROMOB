package francois.fr.applipromob.gameview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import francois.fr.applipromob.R;
import francois.fr.applipromob.objetsJeux.Boat;
import francois.fr.applipromob.objetsJeux.Log;
import francois.fr.applipromob.thread.GameLoopBateau;

public class GameViewBateau extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopBateau gameLoopThread;
    private int score;
    private long startTime;
    private int tpstotal;
    private boolean logAvailable;
    private int vitesse;

    private Boat bateau;
    private Log log;

    int indice;

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    // création de la surface de dessin
    public GameViewBateau(Context context, int indice) {
        super(context);
        this.indice = indice;
        score = 0;
        vitesse = 0;
        tpstotal = 0;
        logAvailable = true;
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopBateau(this, this.getContext());

        //Création du bateau
        bateau = new Boat(this.getContext());
        log = new Log(this.getContext());
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

        canvas.drawText("Temps : " + String.valueOf(tpstotal), (float) (w * 0.1), (float) (h * 0.1), textScore);

        bateau.draw(canvas);
        log.draw(canvas);
    }

    public boolean collision(Log l) {
        return bateau.getX() < l.getX() + l.getLogW() &&
                bateau.getX() + bateau.getBoatW() > l.getX() &&
                bateau.getY() < l.getY() + l.getLogH() &&
                bateau.getBoatH() + bateau.getY() > l.getY();
    }


    // Fonction appelée par la boucle principale (gameLoopThread)
    // On gère ici le déplacement des objets
    public void update() {
        if (logAvailable) {
            logAvailable = false;
            if (vitesse < 10) vitesse = vitesse + 1;
            double randomX = Math.random() * (log.getScreenW() - log.getLogW());
            log.setX((int) randomX);
            log.setY(-log.getScreenH() * 5 / 100);
        } else {
            log.setY(log.getY() + log.getScreenH() * vitesse / 100);
            if (log.getY() >= log.getScreenH() * 110 / 100) logAvailable = true;
        }
        tpstotal = (int) ((System.currentTimeMillis()) - startTime) / 1000;
        if (collision(log)) {
            score = tpstotal * 100;
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
        bateau.resize(w, h);
        log.resize(w, h);
    }

    public Boat getBateau() {
        return bateau;
    }

    public void setTimGV(long time) {
        startTime = time;
    }

    public int getTime() {
        return tpstotal;
    }

    public int getScore() {
        return score;
    }

    public int getIndice() {
        return indice;
    }
}

