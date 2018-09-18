package com.recettes.portalrecettes.persistence;

import com.recettes.portalrecettes.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer>
{

    @Query(value = "SELECT * FROM UTILISATEUR u WHERE u.login = :login",nativeQuery = true)
    User findUserByLogin(@Param("login") String login);

    @Query(value = "SELECT * FROM UTILISATEUR u WHERE u.id = :id",nativeQuery = true)
    User findUserById(@Param("id") int id);
}
