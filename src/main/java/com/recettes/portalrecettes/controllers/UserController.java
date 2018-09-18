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
    private final UserDao userDao;
    private final recettesDao recetteDao;


    public UserController(UserDao userDao, IngredientDao ingreDao,recettesDao recetteDao) {
        this.userDao = userDao;
        this.ingreDao = ingreDao;
        this.recetteDao = recetteDao;
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
        return "";
    }

    @PostMapping("/login")
    public String doLogin(Model model, User user)
    {
        //TODO check name and password then redirect to userhomepage (need to add model to doLogin)
//        User u = userDao.findUserByLogin(user.getLogin());
//        if(u.equals(null))
//        {
//            return "/probleme";
//        }//int id =3;
//        return "account/"+user.getLogin();
        return "";
    }


}
