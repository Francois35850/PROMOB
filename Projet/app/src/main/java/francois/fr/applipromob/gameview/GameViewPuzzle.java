package francois.fr.applipromob.gameview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import francois.fr.applipromob.R;

public class GameViewPuzzle extends AppCompatActivity {

    ImageView piece1,piece2,piece3,piece4,piece5,piece6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_puzzle);

        piece1 = findViewById(R.id.piece1);
        piece2 = findViewById(R.id.piece2);
        piece3 = findViewById(R.id.piece3);
        piece4 = findViewById(R.id.piece4);
        piece5 = findViewById(R.id.piece5);
        piece6 = findViewById(R.id.piece6);

        

    }
}