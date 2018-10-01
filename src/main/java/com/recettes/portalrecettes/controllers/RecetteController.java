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
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecetteController {

    private final IngredientDao ingreDao;
    private final UserDao userDao;
    private final recettesDao recetteDao;

    private User loggedUser;


    public RecetteController(UserDao userDao, IngredientDao ingreDao, recettesDao recetteDao) {
        this.userDao = userDao;
        this.ingreDao = ingreDao;
        this.recetteDao = recetteDao;
    }

    @GetMapping("/user/add_ingredient")
    public String showAddIngredient(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "add_ingredient";
    }

    @GetMapping("/user/add_recipe")
    public String showAddRecipe(Model model) {
        model.addAttribute("recette", new Recettes());
        return "add_recipe";
    }


//    client ajoute plusieurs ingredient dans son frigo (PostMapping)
//    @PostMapping("/account/addIngredient")
//    public String addUserIngredient(String nomIngredient,Model model) {
//        Iterable<Ingredient> listIngredients = ingreDao.findAll();
//        for (Ingredient i : listIngredients) {
//            if (i.getNom().equals(nomIngredient)) {
//                loggedUser.getIngredients().add(i);
//                userDao.save(loggedUser);
//                return "";
//            }
//        }
//        System.out.println("ingredient non supporté !");
//        return "";
//    }

    @PostMapping("/account/addIngredient")
    public ModelAndView addOneUserIngredient(Ingredient ingredient,Model model) {
        Iterable<Ingredient> listIngredients = ingreDao.findAll();
        //existing ingredient in user
        Iterable<Ingredient> listIngredientsUser = loggedUser.getIngredients();
        for (Ingredient i : listIngredientsUser) {
            if (i.getNom().equals(ingredient.getNom())) {
                return new ModelAndView("redirect:/account/" + loggedUser.getId());
            }
        }
        ////existing ingredient in app ingredients
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(ingredient.getNom())) {
                loggedUser.getIngredients().add(i);
                userDao.save(loggedUser);
                return new ModelAndView("redirect:/account/" + loggedUser.getId());
            }
        }
        //non existing ingredient
        ingreDao.save(ingredient);
        loggedUser.getIngredients().add(ingredient);
        userDao.save(loggedUser);
        return new ModelAndView("redirect:/account/" + loggedUser.getId());
    }

    @PostMapping("/account/addRecipe")
    public ModelAndView addRecipe(Recettes recette, Model model) {
        Recettes receteToSave =new Recettes();
        receteToSave.setDescription(recette.getDescription());
        receteToSave.setTitre(recette.getTitre());
        receteToSave.setIngredient(new ArrayList<>());
        receteToSave.setLien_img(null);
        recetteDao.save(receteToSave);
        List<Ingredient> ingredients = new ArrayList<>();
        String[] TempIng = recette.getIngredient().get(0).getNom().split(",");
        for (String t : TempIng) {
            ingredients.add(new Ingredient(t.trim(), ""));
        }
        for (Ingredient i : ingredients) {
            addIngredientToRecipe(receteToSave, i.getNom());
        }
        return new ModelAndView("redirect:/account/" + loggedUser.getId());
    }

    @GetMapping("/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable("id") int id) {
        loggedUser.getIngredients().remove(loggedUser.getUserIngredientById(id));
        userDao.save(loggedUser);
        return "index";
    }

    public void addIngredientToRecipe(Recettes recette, String nomIngredient) {
        Iterable<Ingredient> listIngredients = ingreDao.findAll();
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(nomIngredient)) {
                recette.getIngredient().add(i);
                recetteDao.save(recette);
                return;
            }
        }
        Ingredient ig = new Ingredient(nomIngredient, "");
        Ingredient ingredient = ingreDao.save(ig);
        recette.getIngredient().add(ingredient);
        recetteDao.save(recette);
    }


    @GetMapping("/account/{id}")
    public String showUserHome(Model model, @PathVariable("id") int id) {
        loggedUser = userDao.findUserById(id);
        model.addAttribute("ingredients", userDao.findUserById(id).getIngredients());
        showPossibleRecettes(userDao.findUserById(id), model);
        return "account";
    }


    public void showPossibleRecettes(User user, Model model) {
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
        System.out.println(possibles);
        return;
    }
}
