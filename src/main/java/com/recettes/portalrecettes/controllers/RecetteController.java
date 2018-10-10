package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.models.Images;
import com.recettes.portalrecettes.models.Ingredient;
import com.recettes.portalrecettes.models.Recipes;
import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.persistence.UserDao;
import com.recettes.portalrecettes.persistence.IngredientDao;
import com.recettes.portalrecettes.persistence.RecipeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecetteController {

    private final IngredientDao ingreDao;
    private final UserDao userDao;
    private final RecipeDao recetteDao;

    private User loggedUser;
    private String IMG= "portal-recettes\\src\\main\\resources\\static\\img";

    public RecetteController(UserDao userDao, IngredientDao ingreDao, RecipeDao recetteDao) {
        this.userDao = userDao;
        this.ingreDao = ingreDao;
        this.recetteDao = recetteDao;
    }

    //When an user ask to store an ingredient
    //pick-up this one in the page's areas
    @GetMapping("/user/add_ingredient")
    public String showAddIngredient(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "add_ingredient";
    }

    //When an user ask to add a recipe
    //pick-up this one in the page's areas
    @GetMapping("/user/add_recipe")
    public String showAddRecipe(Model model) {
        model.addAttribute("recette", new Recipes());
        return "add_recipe";
    }

    //When an user ask to add a recipe
    //pick-up this one in the page's areas
    @GetMapping("/user/add_image")
    public String showAddImage(Model model) {
        model.addAttribute("image", new Images());

        return "add_image";
    }

    @PostMapping("/account/addImage")
    public ModelAndView  addImage(Image image, Model model){
        Ingredient Lasting= loggedUser.getLastIngredient();
        Lasting.setLien_img(".\\src\\main\\resources\\static\\img\\" + Lasting.getLien_img() + ".jpg");
        return new ModelAndView("redirect:/account/" + loggedUser.getId());
    }

    //Process the different cases possible when a user add an ingredient
    @PostMapping("/account/addIngredient")
    public ModelAndView addOneUserIngredient(Ingredient ingredient,Model model) {
        Iterable<Ingredient> listIngredients = ingreDao.findAll();
        //existing ingredient in user
        // it's useless to save it twice
        Iterable<Ingredient> listIngredientsUser = loggedUser.getIngredients();
        for (Ingredient i : listIngredientsUser) {
            if (i.getNom().equals(ingredient.getNom())) {
                return new ModelAndView("redirect:/account/" + loggedUser.getId());
            }
        }
        ////existing ingredient in app ingredients
        //add it to the user
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(ingredient.getNom())) {
                loggedUser.getIngredients().add(i);
                userDao.save(loggedUser);
                return new ModelAndView("redirect:/account/" + loggedUser.getId());
            }
        }
        //non existing ingredient
        //add it to the DB and user
        ingreDao.save(ingredient);
        loggedUser.getIngredients().add(ingredient);
        userDao.save(loggedUser);
        return new ModelAndView("redirect:/account/" + loggedUser.getId());
    }


    //Get an set components of the recipe in text areas of the page
    //Use parsing method to put Ingredients in an Array used to call addIngredientToRecipe()
    @PostMapping("/account/addRecipe")
    public ModelAndView addRecipe(Recipes recette, Model model) {
       // File
        Recipes receteToSave =new Recipes();
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
        //if ()
        return new ModelAndView("redirect:/account/" + loggedUser.getId());
    }

    //When user ask to delete one of his Ingredient
    @GetMapping("/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable("id") int id) {
        loggedUser.getIngredients().remove(loggedUser.getUserIngredientById(id));
        userDao.save(loggedUser);
        return "index";
    }

    //Process the different cases possible when  an ingredient is added to a recipe
    public void addIngredientToRecipe(Recipes recette, String nomIngredient) {
        Iterable<Ingredient> listIngredients = ingreDao.findAll();
        for (Ingredient i : listIngredients) {
            ////existing ingredient in app ingredients
            //add it to the recipe
            if (i.getNom().equals(nomIngredient)) {
                recette.getIngredient().add(i);
                recetteDao.save(recette);
                return;
            }
        }
        //non existing ingredient
        //add it to the DB and recipe
        Ingredient ig = new Ingredient(nomIngredient, "");
        Ingredient ingredient = ingreDao.save(ig);
        recette.getIngredient().add(ingredient);
        recetteDao.save(recette);
    }

    //delete ingredient from db, Its not the best way to It, and I know It's not good practice to use get
    //but it works, with the ajax call of the method
    @GetMapping("/account/{id}")
    public String showUserHome(Model model, @PathVariable("id") int id) {
        loggedUser = userDao.findUserById(id);
        model.addAttribute("ingredients", userDao.findUserById(id).getIngredients());
        showPossibleRecettes(userDao.findUserById(id), model);
        return "account";
    }

    //find and return the Recipes for what the user have the necessaries ingredients
    public void showPossibleRecettes(User user, Model model) {
        Boolean flag = true;
        List<Recipes> possibles = new ArrayList<>();
        for (Recipes r : recetteDao.findAll()) {
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
