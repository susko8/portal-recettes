package com.recettes.portalrecettes.controllers;

import com.recettes.portalrecettes.persistence.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


}
