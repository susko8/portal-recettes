package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.persistence.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/registration")
    public String showRegistration()
    {
    return "registration";
    }

    @GetMapping("/connection")
    public String showLogin()
    {
        return "connection";
    }

    @GetMapping("/home")
    public String showHome()
    {
        return "index";
    }

    @GetMapping("/account")
    public String showAccount()
    {
        return "account";
    }

    @PostMapping("/enregistrer")
    public String addUser(User user)
    {
        //TODO ajouter model a showRegistration
        userDao.save(user);
        //TODO redirect to user homepage
        return "";
    }

    @PostMapping("/login")
    public String doLogin()
    {
        //TODO check name and password then redirect to userhomepage (need to add model to doLogin)
        return "";
    }


}
