package com.flore.taskagenda.controllers;

import com.flore.taskagenda.models.ResponseUser;
import com.flore.taskagenda.models.User;
import com.flore.taskagenda.services.ServiceUser;
import com.flore.taskagenda.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUserController {

    /**
     * Services of a user.
     * */
    @Autowired
    private ServiceUser serviceUser;

    /**
     * Util for jwt.
     * */
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login/user")
    public ResponseUser login(@RequestBody User user) {
        User userLogin = serviceUser.findUser(user);
        if (userLogin == null)
            throw new IllegalArgumentException("The email or password aren't correct");

        String token = jwtUtil.create(String.valueOf(userLogin.getId()), userLogin.getEmail());

        ResponseUser response = new ResponseUser();
        response.setTokenJWT(token);
        response.setIdUser(userLogin.getId());
        response.setNameUser(userLogin.getName());
        response.setEmailUser(userLogin.getEmail());

        return response;
    }

    /**
     * Manges the error BAD_REQUEST.
     * @param illegalArgumentException the error.
     * @return {@link HttpStatus} BAD_REQUEST with a message.
     * */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleCustomException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalArgumentException.getMessage());
    }

}