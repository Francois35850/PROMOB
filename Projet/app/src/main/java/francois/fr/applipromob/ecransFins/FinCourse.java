package francois.fr.applipromob.ecransFins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import francois.fr.applipromob.MainActivity;
import francois.fr.applipromob.PlaySound;
import francois.fr.applipromob.R;

public class FinCourse extends AppCompatActivity {

    int image;
    int tps;

    Button quitter;
    ImageView imageRunner;
    TextView infosPuzzle;

    PlaySound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin_course);

        sound = new PlaySound(R.raw.victory, this);

        quitter = findViewById(R.id.quitter);
        imageRunner = findViewById(R.id.imageRunner);
        infosPuzzle = findViewById(R.id.infoCourse);

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        tps = getIntent().getIntExtra("temps", 0);
        image = R.drawable.runner_01;
        imageRunner.setImageDrawable(getResources().getDrawable(image));
        infosPuzzle.setText("Vous avez fini la course en " + tps + " secondes");
    }
}