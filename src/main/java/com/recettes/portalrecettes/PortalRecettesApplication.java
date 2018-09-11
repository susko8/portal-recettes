package com.recettes.portalrecettes;

import com.recettes.portalrecettes.models.User;
import com.recettes.portalrecettes.models.ingredients;
import com.recettes.portalrecettes.persistence.UserDao;
import com.recettes.portalrecettes.persistence.ingredientsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class PortalRecettesApplication
{

    @Autowired
    private UserDao userDao;
    @Autowired
    private ingredientsDao ingredientDao;

    public static void main(String[] args)
    {
        SpringApplication.run(PortalRecettesApplication.class, args);
    }

    @PostConstruct
    public void init()
    {
        userDao.save(new User("samuel","12345","samuel","susoliak"));
        ingredientDao.save(new ingredients("chocolat","liendemonimage"));
    }
}
