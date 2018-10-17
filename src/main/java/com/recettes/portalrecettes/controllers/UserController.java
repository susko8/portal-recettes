package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.persistence.UserDao;
import com.recettes.portalrecettes.persistence.IngredientDao;
import com.recettes.portalrecettes.persistence.RecipeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//Controller used to manage the User's connexion and views
@Controller
public class UserController {

    private final IngredientDao ingreDao;
    private final RecetteController recetteController;
    private final UserDao userDao;
    private final RecipeDao recetteDao;


    public UserController(UserDao userDao, IngredientDao ingreDao, RecipeDao recetteDao, RecetteController recetteController) {
        this.userDao = userDao;
        this.ingreDao = ingreDao;
        this.recetteDao = recetteDao;
        this.recetteController = recetteController;
    }

    //Redirect to the page used to create a new account
    @GetMapping("/registration")
    public String showRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    //Redirect to the page used to connect the user's account
    @GetMapping("/connection")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "connection";
    }

    //Redirect to the index page
    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("data", recetteDao.findAll());
        return "index";
    }

    //Redirect by default to the index page
    @GetMapping("")
    public String showIndex(Model model) {
        model.addAttribute("data", recetteDao.findAll());
        return "index";
    }

    //Redirect to the user's account page
    @GetMapping("/account")
    public String showAccount() {
        return "account";
    }


    @GetMapping("/recette")
    public String showRecette() {
        return "recette";
    }

    //In case of bad connexion
    //Redirect to a temporary page which explain that the connexion didn't work
    //After a few second, the user redirected to the index/home page
    @GetMapping("/probleme")
    public String showProblem() {
        return "probleme";
    }

    //After a registration
    //Redirect to a temporary page which explain that the registration worked
    //After a few second, the user redirected to the connexion page
    @GetMapping("/registration_ok")
    public String showRegistrationSuccess() {
        return "registration_ok";
    }


    //Register the new user's account after he has submited the registration form
    //if there is a problem redirect to the error page
    //if not, redirect to the connexion page
    @PostMapping("/enregistrer")
    public ModelAndView addUser(User user, Model model)
    {
        try {
            User u = userDao.findUserByLogin(user.getLogin());
            if (user.getLogin().equals(u.getLogin())) {
                System.out.println("login deja existant");
            }
            return new ModelAndView("redirect:/probleme");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            userDao.save(user);
            return new ModelAndView("redirect:/registration_ok");
        }

    }

    //After the user has submited the connexion form
    //check if the datas in the form are in adequacy with the DB
    //if there is a problem redirect to the error page
    //if not, redirect to the account page
    @PostMapping("/login")
    public ModelAndView doLogin(Model model, User user) {
        try {
            User u = userDao.findUserByLogin(user.getLogin());
            if (user.getPassword().equals(u.getPassword())) {
                return new ModelAndView("redirect:/account/" + u.getId());
            }
            else
            {
                return new ModelAndView("redirect:/probleme");
            }
        } catch (Exception e) {
            return new ModelAndView("redirect:/probleme");
        }
    }


}
