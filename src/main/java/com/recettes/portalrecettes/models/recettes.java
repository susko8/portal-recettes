package com.recettes.portalrecettes.models;

import javax.persistence.*;

@Entity(name="recettes")
public class recettes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String titre;
    private String description;
    private String lien_img;

    public recettes(String titre, String description, String lien_img) {
        this.titre = titre;
        this.description = description;
        this.lien_img = lien_img;
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

    public String getLien_img() {
        return lien_img;
    }

    public void setLien_img(String lien_img) {
        this.lien_img = lien_img;
    }
}
