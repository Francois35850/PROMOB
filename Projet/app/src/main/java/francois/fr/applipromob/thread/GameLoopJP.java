package francois.fr.applipromob.thread;

import android.content.Context;
import android.view.View;

public class GameLoopJP extends Thread {

    private View view;
    private Context context;
    private boolean running;

    public GameLoopJP(View view, Context context) {
        this.view = view;
        this.context = context;
        this.running = true;
    }

    private void modifierView(int dernierEssai) {

    }

    @Override
    public void run() {

        while(running) {

        }

    }
}
