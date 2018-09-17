package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.models.Ingredient;
import com.recettes.portalrecettes.models.Recettes;
import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.persistence.UserDao;
import com.recettes.portalrecettes.persistence.ingredientsDao;
import com.recettes.portalrecettes.persistence.recettesDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class RecetteController {

    private final ingredientsDao ingreDao;
    private final UserDao userDao;
    private final recettesDao recetteDao;


    public RecetteController(UserDao userDao, ingredientsDao ingreDao,recettesDao recetteDao) {
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
                user.getIngredient().add(i);
                userDao.save(user);
                return "";
            }
        }
        user.getIngredient().add(new Ingredient(nomIngredient,""));
        return "";
    }

//TODO client ajoutre un recette (PostMapping)
//    public String addRecette(User user, )
//    {
//
//    }


//TODO fonction pour montrer tous les recettes sur une page (relier a un page de front-end)
    @GetMapping("/account")
    public String showHome(Model model)
    {
        model.addAttribute("recettes", recetteDao.findAll());
        return "account";
    }


//TODO fonction pour montrer tous les ingredients de frigo de client (relier a un page de front-end)
    public String showIngredients(User user, Model model)
    {
        model.addAttribute("ingredients",user.getIngredient());
        return "";
    }

//TODO fonction pour montrer les recettes que le client peut cuire avec les ingredients de son frigo
public String showPossibleRecettes(User user, Model model)
{
    List<Recettes> possibles = new ArrayList<>();
    for (Recettes r : recetteDao.findAll())
    {
        for(Ingredient i : r.getIngredient())
        {
            Boolean match = false;
            //if()
        }
    }
    model.addAttribute("possibles",possibles);

    return "";
}

//    public  boolean equalLists(List<Ingredient> a, List<Ingredient> b){
//        // Check for sizes and nulls
//
//        if (a == null && b == null) return true;
//
//
//        if ((a == null && b!= null) || (a != null && b== null) || (a.size() != b.size()))
//        {
//            return false;
//        }
//
//        // Sort and compare the two lists
//        Collections.sort(x);
//        Collections.sort();
//        return a.equals(b);
//    }



}
