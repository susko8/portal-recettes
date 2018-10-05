package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.persistence.UserDao;
import com.recettes.portalrecettes.persistence.IngredientDao;
import com.recettes.portalrecettes.persistence.RecettesDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final IngredientDao ingreDao;
    private final RecetteController recetteController;
    private final UserDao userDao;
    private final RecettesDao recetteDao;


    public UserController(UserDao userDao, IngredientDao ingreDao, RecettesDao recetteDao, RecetteController recetteController) {
        this.userDao = userDao;
        this.ingreDao = ingreDao;
        this.recetteDao = recetteDao;
        this.recetteController = recetteController;
    }

    @GetMapping("/registration")
    public String showRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/connection")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "connection";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("data", recetteDao.findAll());
        return "index";
    }

    @GetMapping("")
    public String showIndex(Model model) {
        model.addAttribute("data", recetteDao.findAll());
        return "index";
    }

    @GetMapping("/account")
    public String showAccount() {
        return "account";
    }

    @GetMapping("/recette")
    public String showRecette() {
        return "recette";
    }

    @GetMapping("/probleme")
    public String showProblem() {
        return "probleme";
    }

    @GetMapping("/registration_ok")
    public String showRegistrationSuccess() {
        return "registration_ok";
    }


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
