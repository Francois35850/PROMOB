package francois.fr.applipromob.thread;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.widget.Toast;

import francois.fr.applipromob.MainActivity;
import francois.fr.applipromob.Solo;
import francois.fr.applipromob.ecransFins.FinCourse;
import francois.fr.applipromob.ecransFins.FinPuzzle;
import francois.fr.applipromob.gameview.GameViewCible;
import francois.fr.applipromob.gameview.GameViewCourse;

public class GameLoopCourse extends Thread {

    private Context context;

    // on définit arbitrairement le nombre d'images par secondes à 30
    private final static int FRAMES_PER_SECOND = 30;

    // si on veut X images en 1 seconde, soit en 1000 ms,
    // on doit en afficher une toutes les (1000 / X) ms.
    private final static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    private final GameViewCourse view;
    private boolean running = false; // état du thread, en cours ou non

    // constructeur de l'objet, on lui associe l'objet view passé en paramètre
    public GameLoopCourse(GameViewCourse view, Context c) {
        this.view = view;
        this.context = c;
    }

    // défini l'état du thread : true ou false
    public void setRunning(boolean run) {
        running = run;
    }

    // démarrage du thread
    @Override
    public void run() {
        // déclaration des temps de départ et de pause
        long startTime;
        long sleepTime;

        // boucle tant que running est vrai
        // il devient faux par setRunning(false), notamment lors de l'arrêt de l'application
        // cf : surfaceDestroyed() dans GameView.java
        while (running) {
            // horodatage actuel
            startTime = System.currentTimeMillis();

            // mise à jour du déplacement des ojets dans GameView.update()
            synchronized (view.getHolder()) {
                view.update();
            }

            // Rendu de l'image, tout en vérrouillant l'accès car nous
            // y accédons à partir d'un processus distinct
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.doDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }

            // Calcul du temps de pause, et pause si nécessaire
            // afin de ne réaliser le travail ci-dessus que X fois par secondes
            sleepTime = SKIP_TICKS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime >= 0) {
                    sleep(sleepTime);
                }
            } catch (Exception e) {
            }
        } // boucle while (running)
        Intent activity = new Intent(context, FinCourse.class);
        activity.putExtra("temps", view.getTpstotal());
        activity.putExtra("indice", view.getIndice());
        activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(activity);
    } // public void run()
}
