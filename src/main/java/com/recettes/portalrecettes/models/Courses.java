package com.recettes.portalrecettes.models;

import javax.persistence.*;

@Entity(name="courses")
public class Courses {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int idIngredient;
    private int idUtilisateur;
    private int nombre;
    private int poids;

    public Courses(int idIngredient, int idUtilisateur) {
        this.idIngredient = idIngredient;
        this.idUtilisateur = idUtilisateur;
    }
    public Courses(){

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
