package francois.fr.applipromob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import francois.fr.applipromob.gameview.GameViewCible;

public class Solo extends AppCompatActivity {

    List<Jeu> jeux;
    RecyclerView dataList;
    AdapterMiniJeu adapter;
    Button lancerPartie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);

        jeux = MainActivity.miniJeux;

        FloatingActionButton retour = findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actMain = new Intent(getApplicationContext(), MainActivity.class);
                actMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(actMain);
            }
        });

        lancerPartie = findViewById(R.id.partie);
        lancerPartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.clearJJ();
                int rand = (int) (Math.random() * 6);
                if (rand == 6) rand = 5;
                MainActivity.addJeuxJoues(rand);
                MainActivity.lancerAct(getApplication(), rand, 1);
            }
        });

        dataList = findViewById(R.id.dataList);

        adapter = new AdapterMiniJeu(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);
    }

}

