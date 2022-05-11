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
public class ReponseEntity {
    private int idReponse;
    private int idUser;
    private TestEntity test;
    private ChoixEntity choix;
    private boolean correct;

    public ReponseEntity() {
    }

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public ChoixEntity getChoix() {
        return choix;
    }

    public void setChoix(ChoixEntity choix) {
        this.choix = choix;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "ReponseEntity{" + "idReponse=" + idReponse + ", idUser=" + idUser + ", idTest=" + test.getIdTest() + ", idChoix=" + choix.getIdChoix() + ", correct=" + correct + '}';
    }

    
    
}
