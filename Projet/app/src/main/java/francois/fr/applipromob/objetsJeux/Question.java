package francois.fr.applipromob.objetsJeux;

import java.util.List;

public class Question {

    private String question;
    private String reponse1, reponse2, reponse3, reponse4;
    private int bonneReponse;

    public Question(String question, String reponse1, String reponse2, String reponse3, String reponse4, int bonneReponse) {
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse4 = reponse4;
        this.bonneReponse = bonneReponse;
    }

    public int getBonneReponse() {
        return bonneReponse;
    }

    public String getReponse1() {
        return reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public String getReponse4() {
        return reponse4;
    }

    public String getQuestion() {
        return question;
    }
}
