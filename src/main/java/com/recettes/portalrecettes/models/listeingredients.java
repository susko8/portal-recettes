package com.recettes.portalrecettes.models;



import javax.persistence.*;

@Entity(name="listeingredients")
public class listeingredients {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int idIngredient;
    private int idRecette;
    private int nombre;
    private int poids;

    public listeingredients(int idIngredient, int idRecette) {
        this.idIngredient = idIngredient;
        this.idRecette = idRecette;
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

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }
}
