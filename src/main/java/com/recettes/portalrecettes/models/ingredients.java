package com.recettes.portalrecettes.models;
import javax.persistence.*;

@Entity(name="ingredients")
public class ingredients {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name="nom")
    private String nom;
    @Column(name="lien_img")
    private String lien_img;

    public ingredients(String nom, String lien_img) {
        this.nom = nom;
        this.lien_img = lien_img;
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