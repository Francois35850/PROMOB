package francois.fr.applipromob.gameview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import francois.fr.applipromob.R;
import francois.fr.applipromob.Solo;
import francois.fr.applipromob.ecransFins.FinJP;

public class GameViewJP extends AppCompatActivity {

    List<Button> buttons;
    ImageView effacer, valider;
    TextView monPrix, moins, plus, tentatives;

    String price;
    int reponse; // int compris entre 10 000 et 50 000 (compris)
    int mini, max, nbTry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_jp);

        // Initialisations valeurs
        mini = 10000;
        max = 50000;
        nbTry = 0;
        price = "";
        // On prend un random pour la réponse
        Random r = new Random();
        reponse = 10000 + r.nextInt(40000);
        System.out.println(reponse);

        // Récupération des TextView
        monPrix = findViewById(R.id.mon_prix);
        moins = findViewById(R.id.moins);
        plus = findViewById(R.id.plus);
        tentatives = findViewById(R.id.tentatives);
        actualiserPrix();
        // Initialisation Images (valider et effacer)
        effacer = findViewById(R.id.bEffacer);
        valider = findViewById(R.id.bValider);
        effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (price.length() != 0) deletePrice();
                else
                    Toast.makeText(view.getContext(), "Le nombre a atteint sa taille minimale", Toast.LENGTH_SHORT).show();
                actualiserPrix();
            }
        });
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prixValide()) {
                    int value = Integer.valueOf(price);
                    if (value > reponse) {
                        max = value;
                        plus.setText(String.valueOf(value).substring(0, 2) + " " + String.valueOf(value).substring(2));
                    } else {
                        mini = value;
                        moins.setText(String.valueOf(value).substring(0, 2) + " " + String.valueOf(value).substring(2));
                    }
                    reinitPrice();
                    nbTry++;
                    actualiserPrix();
                    tentatives.setText("Tentatives : " + nbTry);
                    checkResult(value);
                } else
                    Toast.makeText(view.getContext(), "Le nombre n'est pas compris entre " + mini + " et " + max, Toast.LENGTH_SHORT).show();
                actualiserPrix();
            }
        });

        // Initialisation Boutons
        buttons = new ArrayList<>(10); // On remplie le tableau des boutons chiffres
        buttons.add((Button) findViewById(R.id.b0));
        buttons.add((Button) findViewById(R.id.b1));
        buttons.add((Button) findViewById(R.id.b2));
        buttons.add((Button) findViewById(R.id.b3));
        buttons.add((Button) findViewById(R.id.b4));
        buttons.add((Button) findViewById(R.id.b5));
        buttons.add((Button) findViewById(R.id.b6));
        buttons.add((Button) findViewById(R.id.b7));
        buttons.add((Button) findViewById(R.id.b8));
        buttons.add((Button) findViewById(R.id.b9));
        for (int i = 0; i < 10; i++) {
            final String chiffre = String.valueOf(i);
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (price.length() < 5) setPrice(chiffre);
                    else
                        Toast.makeText(view.getContext(), "Le nombre a atteint sa taille maximale", Toast.LENGTH_SHORT).show();
                    actualiserPrix();
                }
            });
        }
    }

    public boolean prixValide() {
        if (price == "") {
            return false;
        } else if (Integer.valueOf(price) >= max || Integer.valueOf(price) <= mini) {
            return false;
        } else return true;
    }

    public void reinitPrice() {
        price = "";
    }

    public void deletePrice() {
        if (this.price.length() == 1) {
            this.price = "";
        } else this.price = this.price.substring(0, this.price.length() - 1);
    }

    public void setPrice(String chiffre) {
        this.price += chiffre;
    }

    public void checkResult(int rep) {
        if (reponse == rep) {
            Toast.makeText(getApplicationContext(), "Bravo, vous avez trouvé en " + nbTry + " tentatives", Toast.LENGTH_SHORT).show();
            Intent retour = new Intent(getApplicationContext(), FinJP.class);
            retour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            retour.putExtra("justePrix", reponse);
            retour.putExtra("try", nbTry);
            startActivity(retour);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void actualiserPrix() {
        String affichage = "";
        char[] tab = price.toCharArray();
        if (tab.length == 1) affichage += tab[0];
        if (tab.length == 2) affichage += tab[0] + " " + tab[1];
        if (tab.length == 3) affichage += tab[0] + " " + tab[1] + " " + tab[2];
        if (tab.length == 4) affichage += tab[0] + "   " + tab[1] + " " + tab[2] + " " + tab[3];
        if (tab.length == 5)
            affichage += tab[0] + " " + tab[1] + "   " + tab[2] + " " + tab[3] + " " + tab[4];
        affichage += " €";
        monPrix.setText(affichage);
        // Il faut que le nombre donné soit compris entre les 2 bornes (exclues)
        if (!prixValide()) {
            monPrix.setTextColor(Color.RED);
        } else monPrix.setTextColor(Color.argb(255, 49, 82, 91));
    }

}
