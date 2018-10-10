package com.recettes.portalrecettes.models;
import javax.persistence.*;

//Object corresponding to an ingredient
//can be present in a recipe or be stored by a user

@Entity(name="ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name="nom")
    private String nom;
    @Column(name="lien_img")
    private String lien_img;

    public Ingredient(String nom, String lien_img) {
        this.nom = nom;
        this.lien_img = lien_img;
    }

    public Ingredient (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLien_img() {
        return lien_img;
    }

    public void setLien_img(String lien_img) {
        this.lien_img = lien_img;
    }

    @Override
    public String toString() {
        return "ingredients{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", lien_img='" + lien_img + '\'' +
                '}';
    }
}
