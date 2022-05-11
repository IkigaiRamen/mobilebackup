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
public class EvaluationEntity {
    private int idEvaluation;
    private int idUser;
    private TestEntity test;
    private int score;
    private int nbrQuestion;
    private boolean success;

    public EvaluationEntity() {
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNbrQuestion() {
        return nbrQuestion;
    }

    public void setNbrQuestion(int nbrQuestion) {
        this.nbrQuestion = nbrQuestion;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "EvaluationEntity{" + "idEvaluation=" + idEvaluation + ", idUser=" + idUser + ", test=" + test + ", score=" + score + ", nbrQuestion=" + nbrQuestion + ", success=" + success + '}';
    }
    
    
    

    
    
    
}
