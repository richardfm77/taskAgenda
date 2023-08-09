package com.flore.taskagenda.repositories;

import com.flore.taskagenda.models.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository to communicate
 * with the database.
 * */
@Repository
public interface RepositoryActivity extends CrudRepository<Activity, Integer> {}
