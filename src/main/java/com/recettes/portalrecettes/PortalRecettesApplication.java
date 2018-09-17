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
public class PortalRecettesApplication
{

    @Autowired
    private UserDao userDao;
    @Autowired
    private ingredientsDao ingredientDao;
    @Autowired
    private coursesDao courseDao;
    @Autowired
    private listeingredientsDao listeDao;
    @Autowired
    private matchesDao matcheDao;
    @Autowired
    private recettesDao recetteDao;


    public static void main(String[] args)
    {
        SpringApplication.run(PortalRecettesApplication.class, args);
    }

    @PostConstruct
    public void init()
    {
        //userDao.save(new User("samuel","12345","samuel","susoliak"));
        //ingredientDao.save(new Ingredient("chocolat","liendemonimage"));
        //recetteDao.save(new Recettes("gateau au chocolat","au talent","liendemonimage"));
        //courseDao.save(new Courses(1,1));
        //listeDao.save(new listeingredients(1,1));
        //matcheDao.save(new matches(1,1));


//        List<Ingredient> listIng = new ArrayList<>();
//        Ingredient item= new Ingredient("chocolat","liendemonimage");
//        listIng.add(item);
//
//        User us = new User("samuel","12345","samuel","susoliak");
//        us.setIngredient(listIng);
//        userDao.save(us);
//
//        Recettes r1 = new Recettes("gateau au chocolat","au talent","liendemonimage");
//        List<Ingredient> listIng2 = new ArrayList<>();
//        Ingredient item2= new Ingredient("chocolat","liendemonimage");
//        listIng.add(item2);
//        r1.setIngredient(listIng2);
//        recetteDao.save(r1);


        User us1 =new User("quentin","miam","quentin","unal");
        User us2 =new User("laurine","1234","laurine","torossian");
        userDao.save(new User("sophie","tasty","sophie","aitis"));

        Recettes r =new Recettes("omellette au fromage","blabla","lien");
        List<Ingredient> listIng = new ArrayList<>();
        listIng.add(new Ingredient("oeuf","liendemonimage"));
        listIng.add(new Ingredient("fromage rapé","liendemonimage"));
        ingredientDao.saveAll(listIng);
        r.setIngredient(listIng);
        us1.setIngredient(listIng);
        recetteDao.save(r);
        userDao.save(us1);
        listIng.clear();
        r=new Recettes("pates carbonara","blabla","lien");
        listIng.add(new Ingredient("parmesan","liendemonimage"));
        listIng.add(new Ingredient("pates","liendemonimage"));
        listIng.add(new Ingredient("lardons","liendemonimage"));
        listIng.add(new Ingredient("crème fraiche","liendemonimage"));
        listIng.add(new Ingredient("échalottes","liendemonimage"));
        listIng.add(new Ingredient("oeuf","liendemonimage"));
        ingredientDao.saveAll(listIng);
        r.setIngredient(listIng);
        recetteDao.save(r);
        us2.setIngredient(listIng);
        userDao.save(us2);

        listIng.sort(Comparator.comparing(Ingredient::getNom));
        System.out.println(listIng.toString());





        //TODO generer les recettes, les ingredients, les clients et leurs frigos




    }
}
