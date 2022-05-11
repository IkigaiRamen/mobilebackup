/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


public class offre {
    private int id;
    private String titre;
    private String description;
    private String responsibilities;
    private String type;
    private String exp;
    private String eduexp;

    private String qualification;
    private String city;

    public offre( String titre, String description, String responsibilities, String type, String exp, String eduexp, String qualification, String city) {
        this.titre = titre;
        this.description = description;
        this.responsibilities = responsibilities;
        this.type = type;
        this.exp = exp;
        this.eduexp = eduexp;
        this.qualification = qualification;
        this.city = city;
    }

    @Override
    public String toString() {
        return "offre{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", responsibilities=" + responsibilities + ", type=" + type + ", exp=" + exp + ", eduexp=" + eduexp + ", qualification=" + qualification + ", city=" + city + '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEduexp() {
        return eduexp;
    }

    public void setEduexp(String eduexp) {
        this.eduexp = eduexp;
    }

    

}