package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.models.Ingredient;
import com.recettes.portalrecettes.models.Recettes;
import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.persistence.UserDao;
import com.recettes.portalrecettes.persistence.IngredientDao;
import com.recettes.portalrecettes.persistence.recettesDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecetteController {

    private final IngredientDao ingreDao;
    private final UserDao userDao;
    private final recettesDao recetteDao;


    public RecetteController(UserDao userDao, IngredientDao ingreDao, recettesDao recetteDao) {
        this.userDao = userDao;
        this.ingreDao = ingreDao;
        this.recetteDao = recetteDao;
    }


    //TODO client ajoute ingredient dans son frigo (PostMapping)
    //@PostMapping
    public String addUserIngredient(User user, String nomIngredient) {
        Iterable<Ingredient> listIngredients = ingreDao.findAll();
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(nomIngredient)) {
                user.getIngredients().add(i);
                userDao.save(user);
                return "";
            }
        }
        System.out.println("ingredient non supporté !");
        return "";
    }
//TODO ouverture du menu de recette
    @GetMapping("/account/{id}/addRecipe")
    public String InstanceRecipe(Model model){
       model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("recette", new Recettes());
        return "account";
    }
//TODO client ajoutre un recette (PostMapping)
    @PostMapping("/account/{id}/addRecipe")
    public String addListIngredientToRecipe( Recettes recette,Ingredient ingredient){
        List <Ingredient> ingredients= new ArrayList<>();
        String[] TempIng= ingredient.getNom().split(",");
        for(String t : TempIng){
            ingredients.add(new Ingredient(t,""));
        }
    for(Ingredient i:ingredients){
        addIngredientToRecipe(recette,i.getNom());
    }
        return "account}";
    }

    public void addIngredientToRecipe(Recettes recette, String nomIngredient){
        Iterable<Ingredient> listIngredients = ingreDao.findAll();
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(nomIngredient)) {
                recette.getIngredient().add(i);
                recetteDao.save(recette);
                return ;
            }
        }
        Ingredient ig= new Ingredient(nomIngredient,"");
        Ingredient ingredient = ingreDao.save(ig);
        recette.getIngredient().add(ingredient);
        recetteDao.save(recette);
    }


    //TODO fonction pour montrer tous les recettes sur une page (relier a un page de front-end)
    @GetMapping("/account/{id}")
    public String showUserHome(Model model, @PathVariable("id") int id)
    {
        model.addAttribute("ingredients", userDao.findUserById(id).getIngredients());
        showPossibleRecettes(userDao.findUserById(id),model);
        return "account";
    }


    //TODO fonction pour montrer tous les ingredients de frigo de client (relier a un page de front-end)
    public String showIngredients(User user, Model model) {
        model.addAttribute("ingredients", user.getIngredients());
        showPossibleRecettes(user,model);
        return "";
    }

    //TODO fonction pour montrer les recettes que le client peut cuire avec les ingredients de son frigo
    public void showPossibleRecettes(User user, Model model)
    {
        Boolean flag = true;
        List<Recettes> possibles = new ArrayList<>();
        for (Recettes r : recetteDao.findAll()) {
            for (Ingredient i : r.getIngredient()) {
                if (!user.hasIngredient(i.getNom())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                possibles.add(r);
            }
            flag = true;
        }
        model.addAttribute("possibles", possibles);
        return ;
    }

//    @PostMapping()
//    public void addIngredientToRecipe(Recettes recette, String nomIngredient){
//        Iterable<Ingredient> listIngredients = ingreDao.findAll();
//        for (Ingredient i : listIngredients) {
//            if (i.getNom().equals(nomIngredient)) {
//                recette.getIngredient().add(i);
//                recetteDao.save(recette);
//                return ;
//            }
//        }
//        Ingredient ig= new Ingredient(nomIngredient,"");
//        Ingredient ingredient = ingreDao.save(ig);
//        recette.getIngredient().add(ingredient);
//        recetteDao.save(recette);
//    }

}
