package com.recettes.portalrecettes.models;

import javax.persistence.*;
import java.util.List;

@Entity(name="recettes")
public class Recettes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String titre;
    private String description;
    private String lien_img;

    @OneToMany(fetch = FetchType.LAZY)
    List<Ingredient> Ingredient;

    public Recettes() {
    }

    public Recettes(String titre, String description, String lien_img, List<com.recettes.portalrecettes.models.Ingredient> ingredient) {
        this.titre = titre;
        this.description = description;
        this.lien_img = lien_img;
        Ingredient = ingredient;
    }

    public List<com.recettes.portalrecettes.models.Ingredient> getIngredient() {
        return Ingredient;
    }

    public void setIngredient(List<com.recettes.portalrecettes.models.Ingredient> ingredient) {
        Ingredient = ingredient;
    }

    public Recettes(String titre, String description, String lien_img) {
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
