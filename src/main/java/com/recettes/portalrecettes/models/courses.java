package com.recettes.portalrecettes.models;

import javax.persistence.*;

@Entity(name="courses")
public class courses {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int idIngredient;
    private int idUtilisateur;
    private int nombre;
    private int poids;

    public courses(int idIngredient, int idUtilisateur) {
        this.idIngredient = idIngredient;
        this.idUtilisateur = idUtilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
