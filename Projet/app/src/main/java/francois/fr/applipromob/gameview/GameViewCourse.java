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
import francois.fr.applipromob.objetsJeux.Runner;
import francois.fr.applipromob.objetsJeux.Wind;
import francois.fr.applipromob.objetsJeux.finishLine;
import francois.fr.applipromob.thread.GameLoopCourse;

public class GameViewCourse extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopCourse gameLoopThread;
    private int score;
    private long startTime;
    private int tpstotal;


    public Runner runner;
    private Wind wind;
    private finishLine fLine;

    int indice;


    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    // création de la surface de dessin
    public GameViewCourse(Context context, int indice) {
        super(context);
        this.indice = indice;
        score = 0;
        tpstotal = 0;
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopCourse(this, this.getContext());


        //création de notre runner
        runner = new Runner(this.getContext());

        //Création de notre wind
        wind = new Wind(this.getContext());

        //Création de la ligne d'arrivée
        fLine = new finishLine(this.getContext());

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

        canvas.drawText("Avancez en secouant votre téléphone.", (float) (w * 0.1), (float) (h * 0.9), textScore);
        canvas.drawText("Si il y a du vent, arrêtez de bouger.", (float) (w * 0.1), (float) (h * 0.95), textScore);

        //canvas.drawText("Time : " + String.valueOf(time), (float) (w * 0.1), (float) (h * 0.1), textScore);
        canvas.drawText("Temps : " + String.valueOf(tpstotal), (float) (w * 0.1), (float) (h * 0.1), textScore);

        // on dessine le coureur
        fLine.draw(canvas);
        runner.draw(canvas);
        if (wind.isActive()) {
            wind.draw(canvas);
        }
    }


    // Fonction appelée par la boucle principale (gameLoopThread)
    // On gère ici le déplacement des objets
    public void update() {
        runner.setImage(this.getContext(), runner.getRunnerW(), runner.getRunnerH());
        tpstotal = (int) ((System.currentTimeMillis()) - startTime) / 1000;

        if (runner.getX() >= runner.getScreenW() * 90 / 100) {
            score = 1500 - tpstotal * 100;
            if (score < 0) score = 0;
            gameLoopThread.setRunning(false);
        }
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

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        runner.resize(w, h); // on définit la taille de la cible selon la taille de l'écran
        wind.resize(w, h);
        fLine.resize(w, h);
    }

    public int getTpstotal() {
        return tpstotal;
    }

    public Runner getRunner() {
        return runner;
    }

    public Wind getWind() {
        return wind;
    }

    public void setTimGV(long time) {
        startTime = time;
    }

    public int getIndice() {
        return indice;
    }

}

