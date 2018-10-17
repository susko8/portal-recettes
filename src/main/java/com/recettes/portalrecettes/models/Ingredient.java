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
    private String name;
    @Column(name="lien_img")
    private String link_img;

    public Ingredient(String name, String link_img) {
        this.name = name;
        this.link_img = link_img;
    }

    public Ingredient (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    @Override
    public String toString() {
        return "ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link_img='" + link_img + '\'' +
                '}';
    }
}
