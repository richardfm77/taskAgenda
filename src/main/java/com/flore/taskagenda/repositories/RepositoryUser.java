package com.flore.taskagenda.repositories;

import com.flore.taskagenda.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Activity repository to communicate
 * with the database.
 * */
@Repository
public interface RepositoryUser extends CrudRepository<User, Integer> {}
