package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.recettes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface recettesDao extends CrudRepository<recettes, Integer>{
}
