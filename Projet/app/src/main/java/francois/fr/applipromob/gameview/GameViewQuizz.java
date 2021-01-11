package francois.fr.applipromob.gameview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import francois.fr.applipromob.MainActivity;
import francois.fr.applipromob.R;
import francois.fr.applipromob.ecransFins.FinQuizz;
import francois.fr.applipromob.objetsJeux.Question;

public class GameViewQuizz extends AppCompatActivity {

    TextView numeroQuestion, reussite;
    TextView question;
    Button reponse1, reponse2, reponse3, reponse4;
    List<Question> questionList = new ArrayList<>();

    private int num = 0;
    private List<Integer> numChoisis = new ArrayList<>();
    private Question qActuelle;
    private int score;
    int time = 0;

    public void initialisation() {
        numeroQuestion = findViewById(R.id.numeroQuestion);
        reussite = findViewById(R.id.score);
        question = findViewById(R.id.question);
        reponse1 = findViewById(R.id.reponse1);
        reponse2 = findViewById(R.id.reponse2);
        reponse3 = findViewById(R.id.reponse3);
        reponse4 = findViewById(R.id.reponse4);
        score = 0;
        questionList.add(new Question("Quel mammifère a le plus long orgasme ?", "L'homme", "Le cochon", "L'éléphant", "La vache", 2));
        questionList.add(new Question("Combien d'yeux ont les abeilles ?", "1", "2", "4", "5", 4));
        questionList.add(new Question("Le record du nom le plus long est détenu par un habitant de :", "Kyoto", "Rio de Janeiro", "Munich", "Bangkok", 4));
        questionList.add(new Question("En moyenne, combien de temps passe une personne sur son téléphone au cours de sa vie ?", "3 mois", "6 mois", "2 ans", "10 ans", 3));
        questionList.add(new Question("Quel est le record de la personne ayant passé le plus de temps sans dormir ?", "2 jours", "4 jours", "7 jours", "11 jours", 4));
        questionList.add(new Question("Quel est le jouet le plus vendu de tous les temps ?", "Le Rubik's cube", "Furby", "Barbie", "SOS Ouistiti", 1));
        questionList.add(new Question("Combien de kilo de chocolat l'allemand moyen mange-t-il par an ?", "3,7 kg", "5,8 kg", "9,1 kg", "11,3 kg", 4));
        questionList.add(new Question("Combien y a-t-il de marches dans la Tour Eiffel", "503", "1710", "2100", "3006", 2));
        questionList.add(new Question("Quel est le nom de bateau le plus répandu ?", "Grace", "Nauti", "Aquaholic", "Seas the day", 1));
        questionList.add(new Question("Combien d'emails sont envoyés chaque seconde dans le monde ?", "0,7 million", "1 million", "2 millions", "4,5 millions", 3));

        reponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qActuelle.getBonneReponse() == 1) {
                    score++;
                    reponse1.setBackgroundColor(Color.GREEN);
                } else {
                    reponse1.setBackgroundColor(Color.RED);
                    allumerBonneReponse();
                }
                questionSuivante();
            }
        });
        reponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qActuelle.getBonneReponse() == 2) {
                    score++;
                    reponse2.setBackgroundColor(Color.GREEN);
                } else {
                    reponse2.setBackgroundColor(Color.RED);
                    allumerBonneReponse();
                }
                questionSuivante();
            }
        });
        reponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qActuelle.getBonneReponse() == 3) {
                    score++;
                    reponse3.setBackgroundColor(Color.GREEN);
                } else {
                    reponse3.setBackgroundColor(Color.RED);
                    allumerBonneReponse();
                }
                questionSuivante();
            }
        });
        reponse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qActuelle.getBonneReponse() == 4) {
                    score++;
                    reponse4.setBackgroundColor(Color.GREEN);
                } else {
                    reponse4.setBackgroundColor(Color.RED);
                    allumerBonneReponse();
                }
                questionSuivante();
            }
        });
    }

    public void allumerBonneReponse() {
        switch (qActuelle.getBonneReponse()) {
            case 1:
                reponse1.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                reponse2.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                reponse3.setBackgroundColor(Color.GREEN);
                break;
            case 4:
                reponse4.setBackgroundColor(Color.GREEN);
                break;
            default:
        }
    }

    public void questionSuivante() {
        new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long l) {
                time--;
            }

            @Override
            public void onFinish() {
                time = 2;
                reponse1.setBackground(getResources().getDrawable(R.drawable.custom_container));
                reponse2.setBackground(getResources().getDrawable(R.drawable.custom_container));
                reponse3.setBackground(getResources().getDrawable(R.drawable.custom_container));
                reponse4.setBackground(getResources().getDrawable(R.drawable.custom_container));
                num++;
                if (num == 6) {
                    finJeu();
                } else {
                    // Choix nouvelle Question
                    int rand = (int) (Math.random() * 10);
                    if (rand == 10) rand = 9;
                    while (numChoisis.contains(rand)) {
                        rand = (int) (Math.random() * 10);
                        if (rand == 10) rand = 9;
                    }
                    qActuelle = questionList.get(rand);
                    numChoisis.add(rand);
                    // Actualisation des éléments en fonction de la nouvelle question
                    reussite.setText(score + " / " + (num - 1));
                    numeroQuestion.setText("Question n°" + num);
                    question.setText(qActuelle.getQuestion());
                    reponse1.setText(qActuelle.getReponse1());
                    reponse2.setText(qActuelle.getReponse2());
                    reponse3.setText(qActuelle.getReponse3());
                    reponse4.setText(qActuelle.getReponse4());
                }
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_quizz);
        initialisation();
        questionSuivante();
    }

    public void finJeu() {
        int indice = getIntent().getIntExtra("indice", -1);
        Intent retour = new Intent(getApplicationContext(), FinQuizz.class);
        retour.putExtra("score", score);
        retour.putExtra("indice", indice);
        retour.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(retour);
    }

}