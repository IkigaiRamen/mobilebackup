/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author zewaf
 */
public class ChoixEntity {
    private int idChoix;
    //foreign key
    private QuestionEntity question;
    private boolean correct;
    private String contenu;

    public ChoixEntity() {
    }

    public int getIdChoix() {
        return idChoix;
    }

    public void setIdChoix(int idChoix) {
        this.idChoix = idChoix;
    }

    public String getContenu() {
        return contenu;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    
    @Override
    public String toString() {
        return '\n' + "ChoixEntity{" + "idChoix=" + idChoix + ", idQuestion=" + question.getIdQuestion() + 
                ", correct=" + correct + ", contenu=" + contenu + '}' ;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChoixEntity other = (ChoixEntity) obj;
        if (this.idChoix != other.idChoix) {
            return false;
        }
        return true;
    }
    

    
    
}
