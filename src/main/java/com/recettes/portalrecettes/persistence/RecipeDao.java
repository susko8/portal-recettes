package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.Recipes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDao extends CrudRepository<Recipes, Integer>{
}
