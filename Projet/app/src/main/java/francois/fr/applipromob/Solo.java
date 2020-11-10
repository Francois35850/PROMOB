package francois.fr.applipromob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import francois.fr.applipromob.jeux.JustePrix;

public class Solo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton retour = findViewById(R.id.retour);
        Button lancementGame = findViewById(R.id.startGameSolo);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        lancementGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity = new Intent(getApplicationContext(), Jeu.class);
                startActivity(activity);
            }
        });

    }
}