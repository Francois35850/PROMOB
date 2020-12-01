package francois.fr.applipromob.gameview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import francois.fr.applipromob.AdapterPiecePuzzle;
import francois.fr.applipromob.R;
import francois.fr.applipromob.Solo;
import francois.fr.applipromob.objetsJeux.PiecePuzzle;
import francois.fr.applipromob.objetsJeux.Puzzle;

public class GameViewPuzzle extends AppCompatActivity {

    List<Puzzle> puzzles = new ArrayList<>();
    List<ImageView> pieces = new ArrayList<>();
    List<ImageView> cadres = new ArrayList<>();

    public static Puzzle puzzleChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_puzzle);
        initPuzzles();
        puzzleChoisi = puzzles.get(0);
        initViews();
        System.out.println("Initialisations terminées");
    }

    private View.OnDragListener getListener(final int position) {
        return new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
                int dragEvent = event.getAction();
                switch (dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        final View v = (View) event.getLocalState();
                        if (v.getId() == position) {
                            pieces.get(position).setVisibility(View.VISIBLE);
                            puzzleChoisi.getLp().get(position).setVisible(false);
                            v.setVisibility(View.INVISIBLE);
                            if (puzzleChoisi.termine()) {
                                Intent activity = new Intent(getApplicationContext(), Solo.class);
                                activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(activity);
                            }
                        }
                        break;
                }
                return true;
            }
        };
    }

    private void initViews() {
        pieces.add((ImageView) findViewById(R.id.p1));
        cadres.add((ImageView) findViewById(R.id.pos1));

        pieces.add((ImageView) findViewById(R.id.p2));
        cadres.add((ImageView) findViewById(R.id.pos2));

        pieces.add((ImageView) findViewById(R.id.p3));
        cadres.add((ImageView) findViewById(R.id.pos3));

        pieces.add((ImageView) findViewById(R.id.p4));
        cadres.add((ImageView) findViewById(R.id.pos4));

        pieces.add((ImageView) findViewById(R.id.p5));
        cadres.add((ImageView) findViewById(R.id.pos5));

        pieces.add((ImageView) findViewById(R.id.p6));
        cadres.add((ImageView) findViewById(R.id.pos6));

        pieces.add((ImageView) findViewById(R.id.p7));
        cadres.add((ImageView) findViewById(R.id.pos7));

        pieces.add((ImageView) findViewById(R.id.p8));
        cadres.add((ImageView) findViewById(R.id.pos8));

        pieces.add((ImageView) findViewById(R.id.p9));
        cadres.add((ImageView) findViewById(R.id.pos9));

        pieces.add((ImageView) findViewById(R.id.p10));
        cadres.add((ImageView) findViewById(R.id.pos10));

        pieces.add((ImageView) findViewById(R.id.p11));
        cadres.add((ImageView) findViewById(R.id.pos11));

        pieces.add((ImageView) findViewById(R.id.p12));
        cadres.add((ImageView) findViewById(R.id.pos12));

        pieces.add((ImageView) findViewById(R.id.p13));
        cadres.add((ImageView) findViewById(R.id.pos13));

        pieces.add((ImageView) findViewById(R.id.p14));
        cadres.add((ImageView) findViewById(R.id.pos14));

        pieces.add((ImageView) findViewById(R.id.p15));
        cadres.add((ImageView) findViewById(R.id.pos15));

        pieces.add((ImageView) findViewById(R.id.p16));
        cadres.add((ImageView) findViewById(R.id.pos16));

        pieces.add((ImageView) findViewById(R.id.p17));
        cadres.add((ImageView) findViewById(R.id.pos17));

        pieces.add((ImageView) findViewById(R.id.p18));
        cadres.add((ImageView) findViewById(R.id.pos18));

        pieces.add((ImageView) findViewById(R.id.p19));
        cadres.add((ImageView) findViewById(R.id.pos19));

        pieces.add((ImageView) findViewById(R.id.p20));
        cadres.add((ImageView) findViewById(R.id.pos20));

        pieces.add((ImageView) findViewById(R.id.p21));
        cadres.add((ImageView) findViewById(R.id.pos21));

        pieces.add((ImageView) findViewById(R.id.p22));
        cadres.add((ImageView) findViewById(R.id.pos22));

        pieces.add((ImageView) findViewById(R.id.p23));
        cadres.add((ImageView) findViewById(R.id.pos23));

        pieces.add((ImageView) findViewById(R.id.p24));
        cadres.add((ImageView) findViewById(R.id.pos24));

        pieces.add((ImageView) findViewById(R.id.p25));
        cadres.add((ImageView) findViewById(R.id.pos25));

        pieces.add((ImageView) findViewById(R.id.p26));
        cadres.add((ImageView) findViewById(R.id.pos26));

        pieces.add((ImageView) findViewById(R.id.p27));
        cadres.add((ImageView) findViewById(R.id.pos27));

        pieces.add((ImageView) findViewById(R.id.p28));
        cadres.add((ImageView) findViewById(R.id.pos28));

        pieces.add((ImageView) findViewById(R.id.p29));
        cadres.add((ImageView) findViewById(R.id.pos29));

        pieces.add((ImageView) findViewById(R.id.p30));
        cadres.add((ImageView) findViewById(R.id.pos30));

        for (int i = 0; i < cadres.size(); i++) {
            cadres.get(i).setOnDragListener(getListener(i));
        }
    }

    private void initPuzzles() {
        ArrayList<PiecePuzzle> piecesWinnie = new ArrayList<>();
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie1), 1));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie2), 2));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie3), 3));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie4), 4));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie5), 5));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie6), 6));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie7), 7));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie8), 8));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie9), 9));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie10), 10));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie11), 11));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie12), 12));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie13), 13));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie14), 14));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie15), 15));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie16), 16));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie17), 17));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie18), 18));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie19), 19));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie20), 20));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie21), 21));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie22), 22));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie23), 23));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie24), 24));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie25), 25));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie26), 26));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie27), 27));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie28), 28));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie29), 29));
        piecesWinnie.add(new PiecePuzzle(getResources().getDrawable(R.drawable.winnie30), 30));
        Puzzle winnie = new Puzzle("Winnie", getResources().getDrawable(R.drawable.winnie_resultat), piecesWinnie);
        winnie.melangePieces();
        puzzles.add(winnie);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView r = findViewById(R.id.recyclerView);
        r.setLayoutManager(layoutManager);
        AdapterPiecePuzzle adapter = new AdapterPiecePuzzle(this);
        r.setAdapter(adapter);
    }

}