package com.recettes.portalrecettes.models;

import javax.persistence.*;

@Entity(name="matches")
public class matches {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int idRecette;
    private int idUtilisateur;

    public matches(int idRecette, int idUtilisateur) {
        this.idRecette = idRecette;
        this.idUtilisateur = idUtilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
