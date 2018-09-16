package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ingredientsDao extends CrudRepository<Ingredient, Integer>{
}
