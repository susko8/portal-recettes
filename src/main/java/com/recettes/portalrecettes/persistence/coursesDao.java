package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.courses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface coursesDao extends CrudRepository<courses, Integer>{
}