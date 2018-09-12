package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.listeingredients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface listeingredientsDao extends CrudRepository<listeingredients, Integer>{
}
