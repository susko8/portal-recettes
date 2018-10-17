package com.recettes.portalrecettes.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Object corresponding to a recipe
// store a list of objects Ingredient necessary to make it
@Entity(name="recettes")
public class Recipes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name="titre")
    private String title;
    private String description;
    @Column(name="lien_img")
    private String link_img;

    //the list of Ingredients needed to make the recipe
    @ManyToMany(fetch = FetchType.EAGER)
    List<Ingredient> Ingredient;

    public Recipes() {
    }

    public Recipes(String title, String description, String link_img, List<com.recettes.portalrecettes.models.Ingredient> ingredient) {
        this.title = title;
        this.description = description;
        this.link_img = link_img;
        Ingredient = ingredient;
    }

    public List<com.recettes.portalrecettes.models.Ingredient> getIngredient() {
        return Ingredient;
    }

    //add the ingredient in argument to the recipe's list of ingredients
    public void setIngredient(List<com.recettes.portalrecettes.models.Ingredient> ingredient) {
        if(this.Ingredient==null){
            this.Ingredient= new ArrayList<>();
        }
        for (Ingredient i : ingredient) {
            this.Ingredient.add(i);
        }

    }

    public Recipes(String title, String description, String link_img) {
        this.title = title;
        this.description = description;
        this.link_img = link_img;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link_img='" + link_img + '\'' +
                ", Ingredient=" + Ingredient +
                '}';
    }
}

