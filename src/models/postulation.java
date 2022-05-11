/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Wassef
 */
public class postulation {
    private int id;
    private String contenu;
    private String email;
    private String psudeo;

    public postulation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsudeo() {
        return psudeo;
    }

    public void setPsudeo(String psudeo) {
        this.psudeo = psudeo;
    }
    
    
}
