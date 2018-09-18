package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.persistence.UserDao;
import com.recettes.portalrecettes.persistence.IngredientDao;
import com.recettes.portalrecettes.persistence.recettesDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final IngredientDao ingreDao;
    private final RecetteController recetteController;
    private final UserDao userDao;
    private final recettesDao recetteDao;


    public UserController(UserDao userDao, IngredientDao ingreDao,recettesDao recetteDao, RecetteController recetteController) {
        this.userDao = userDao;
        this.ingreDao = ingreDao;
        this.recetteDao = recetteDao;
        this.recetteController = recetteController;
    }

    @GetMapping("/registration")
    public String showRegistration()
    {
    return "registration";
    }

    @GetMapping("/connection")
    public String showLogin(Model model)
    {
        model.addAttribute("user",new User() );
        return "connection";
    }

    @GetMapping("/home")
    public String showHome(Model model)
    {
        model.addAttribute("data", recetteDao.findAll());
        return "index";
    }

    @GetMapping("")
    public String showIndex(Model model)
    {
        model.addAttribute("data", recetteDao.findAll());
        return "index";
    }

    @GetMapping("/account")
    public String showAccount()
    {
        return "account";
    }

    @GetMapping("/recette")
    public String showRecette()
    {
        return "recette";
    }

    @PostMapping("/enregistrer")
    public String addUser(User user)
    {
        //TODO ajouter model et showRegistration
        userDao.save(user);
        //TODO redirect to registration success page
        return "connection";
    }

    @PostMapping("/login")
    public String doLogin(Model model, User user)
    {
        //TODO check password
        try {
            User u = userDao.findUserByLogin(user.getLogin());
            return  recetteController.showHome(model,u.getId());
           // return "account/"+u.getId();
        }
        catch (Exception e)
        {
            return "/probleme";
        }
    }


}
