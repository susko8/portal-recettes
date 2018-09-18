package com.recettes.portalrecettes;

import com.recettes.portalrecettes.models.*;
import com.recettes.portalrecettes.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;

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
    public void addListIngredientToUser(User user, List<Ingredient> ingredients){
        for(Ingredient i:ingredients){
            addIngredientToUser(user,i.getNom());
        }

    }

    /*public void addIngredientToRecipe(Recettes recette, String nomIngredient){
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
    }*/

    @PostConstruct
    public void init() {
        User us1 = new User("quentin@gmail.com", "miam", "quentin", "unal");
        User us2 = new User("laurine@gmail.com", "1234", "laurine", "torossian");
        User us3 = new User("sophie@gmail.com", "tasty", "sophie", "aitis");

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
        //listIng.add(new Ingredient("oeuf", "liendemonimage"));
        ingredientDao.saveAll(listIng);
        r.setIngredient(listIng);
        recetteDao.save(r);
        us2.setIngredients(listIng);
        us3.setIngredients(listIng);
        userDao.save(us3);
        userDao.save(us2);

        listIng.clear();
        listIng.add(new Ingredient("fraises",""));
        listIng.add(new Ingredient("framboises",""));
        ingredientDao.saveAll(listIng);
       // addIngredientToRecipe(r,"parmesan");

       // System.out.println(userDao.findUserById(11);

    }
}
