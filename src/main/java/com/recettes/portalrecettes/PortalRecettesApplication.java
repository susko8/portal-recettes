package com.recettes.portalrecettes;

import com.recettes.portalrecettes.models.*;
import com.recettes.portalrecettes.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class PortalRecettesApplication {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IngredientDao ingredientDao;
    @Autowired
    private recettesDao recetteDao;


    public static void main(String[] args) {
        SpringApplication.run(PortalRecettesApplication.class, args);
    }

    public void addIngredientToUser(User user, String nomIngredient) {
        Iterable<Ingredient> listIngredients = ingredientDao.findAll();
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(nomIngredient)) {
                user.getIngredients().add(i);
                userDao.save(user);
                return;
            }
        }
        System.out.println("ingredient non supporté !");

    }

    public void addIngredientToRecipe(Recettes recette, String nomIngredient){
        Iterable<Ingredient> listIngredients = ingredientDao.findAll();
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(nomIngredient)) {
                recette.getIngredient().add(i);
                recetteDao.save(recette);
                return ;
            }
        }
        Ingredient ig= new Ingredient(nomIngredient,"");
        Ingredient ingredient = ingredientDao.save(ig);
        recette.getIngredient().add(ingredient);
        recetteDao.save(recette);
    }

    @PostConstruct
    public void init() {
        //userDao.save(new User("samuel","12345","samuel","susoliak"));
        //ingredientDao.save(new Ingredient("chocolat","liendemonimage"));
        //recetteDao.save(new Recettes("gateau au chocolat","au talent","liendemonimage"));




//        List<Ingredient> listIng = new ArrayList<>();
//        Ingredient item= new Ingredient("chocolat","liendemonimage");
//        listIng.add(item);
//
//        User us = new User("samuel","12345","samuel","susoliak");
//        us.setIngredients(listIng);
//        userDao.save(us);
//
//        Recettes r1 = new Recettes("gateau au chocolat","au talent","liendemonimage");
//        List<Ingredient> listIng2 = new ArrayList<>();
//        Ingredient item2= new Ingredient("chocolat","liendemonimage");
//        listIng.add(item2);
//        r1.setIngredients(listIng2);
//        recetteDao.save(r1);


        User us1 = new User("quentin", "miam", "quentin", "unal");
        User us2 = new User("laurine", "1234", "laurine", "torossian");
        userDao.save(new User("sophie", "tasty", "sophie", "aitis"));

        Recettes r = new Recettes("omellette au fromage", "blabla", "lien");
        List<Ingredient> listIng = new ArrayList<>();
        listIng.add(new Ingredient("oeuf", "liendemonimage"));
        listIng.add(new Ingredient("fromage rapé", "liendemonimage"));
        ingredientDao.saveAll(listIng);
        r.setIngredient(listIng);
        us1.setIngredients(listIng);
        recetteDao.save(r);
        userDao.save(us1);
        listIng.clear();
        r = new Recettes("pates carbonara", "blabla", "lien");
        listIng.add(new Ingredient("parmesan", "liendemonimage"));
        listIng.add(new Ingredient("pates", "liendemonimage"));
        listIng.add(new Ingredient("lardons", "liendemonimage"));
        listIng.add(new Ingredient("crème fraiche", "liendemonimage"));
        listIng.add(new Ingredient("échalottes", "liendemonimage"));
        listIng.add(new Ingredient("oeuf", "liendemonimage"));
        ingredientDao.saveAll(listIng);
        r.setIngredient(listIng);
        recetteDao.save(r);
        us2.setIngredients(listIng);
        userDao.save(us2);

        addIngredientToUser(us1, "patate");
        addIngredientToUser(us2, "pates");


        //TODO generer les recettes, les ingredients, les clients et leurs frigos


    }
}
