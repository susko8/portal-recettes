package com.recettes.portalrecettes.models;

import javax.persistence.*;
import java.util.List;

@Entity(name="utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String login;
    private String password;

    @Column(name="prenom")
    private String name;
    @Column(name="nom")
    private String surname;

    @OneToMany(fetch = FetchType.LAZY)
    List<Ingredient> Ingredient;

    public List<Ingredient> getIngredient() {
        return Ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.Ingredient = ingredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected User() {}

    public User(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString()
    {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s']",
                id, login, password);
    }
}
