package com.flore.taskagenda.services;

import com.flore.taskagenda.models.User;
import com.flore.taskagenda.repositories.RepositoryUser;
import com.flore.taskagenda.utils.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Manges the services of a user.
 * */
@Service
public class ServiceUser {

    /**
     * Repository of user.
     */
    @Autowired
    private RepositoryUser repositoryUser;

    /**
     * Find a user by email and password.
     * @param user @class {@link User}.
     * @return user @class {@link User}.
     * */
    public User findUser(User user) {
        List<User> users = (List<User>) repositoryUser.findAll();
        for (User userIterable : users) {
            String emailIterable = userIterable.getEmail();
            String email = user.getEmail();

            String passwordIterable = userIterable.getPassword();
            String password = user.getPassword();
            if (emailIterable.equals(email) && Hash.verifyHash(passwordIterable, password))
                return userIterable;
        }
        return null;
    }

    /**
     * Register a user to be able to log in.
     * @param newUser @class {@link User}
     * @return true if the user's email already exists,
     * false in other case.
     * */
    public boolean newUser(User newUser) {
        List<User> users = (List<User>) repositoryUser.findAll();

        for (User user : users) {
            if (user.getEmail().equals(newUser.getEmail()))
                return true;
        }

        String passwordHash = Hash.hashing(newUser.getPassword());
        newUser.setPassword(passwordHash);

        repositoryUser.save(newUser);

        return false;
    }

}
