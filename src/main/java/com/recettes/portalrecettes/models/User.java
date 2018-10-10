package com.recettes.portalrecettes.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Object corresponding to an user of the website
// can store objects Ingredient
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

    @ManyToMany(fetch = FetchType.EAGER)
    List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

   public Ingredient getLastIngredient()
    {
        Ingredient LastIngredient= new Ingredient();
        LastIngredient.setId(0);
        for (Ingredient i :this.getIngredients()){
            if(LastIngredient.getId()<i.getId()) {
                LastIngredient=i;
            }
        }
        return LastIngredient;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        if(this.ingredients==null){
            this.ingredients= new ArrayList<>();
        }
        for (Ingredient i : ingredients) {
            this.ingredients.add(i);
        }

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

    public User() {}

    public Boolean hasIngredient(String name)
    {
        for(int i = 0 ; i<this.ingredients.size();i++)
        {
            if(name.equals(this.ingredients.get(i).getNom()))
                return true;
        }
        return false;
    }

    public Ingredient getUserIngredientById(int id)
    {
        for(int i = 0 ; i<this.ingredients.size();i++)
        {
            if(id==this.getIngredients().get(i).getId())
                return this.getIngredients().get(i);
        }
        return null;
    }

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
