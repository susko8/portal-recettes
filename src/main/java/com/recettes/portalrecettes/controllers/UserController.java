package com.recettes.portalrecettes.controllers;

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

    @PostMapping("/login")
    public String doLogin()
    {
        return "";
    }


}
