package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.Courses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesDao extends CrudRepository<Courses, Integer>{
}
