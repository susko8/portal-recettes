package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.matches;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface matchesDao extends CrudRepository<matches, Integer>{
}
