package com.flore.taskagenda.controllers;

import com.flore.taskagenda.models.User;
import com.flore.taskagenda.services.ServiceUser;
import com.flore.taskagenda.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Manges the urls of user.
 * */
@Controller
@RequestMapping("/user")
public class ControllerUser {

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

    /**
     * @return the view login.
     * */
    @GetMapping("/login")
    public String getLoginView(){
        return "login";
    }

    /**
     * @return the view activity.
     * */
    @GetMapping("/activity")
    public String getActivityView(){
        return "activity";
    }

    /**
     * @return the view register.
     * */
    @GetMapping("/register")
    public String getRegisterView() {
        return "register";
    }

    /**
     * Register a user to be able to log in.
     * @param newUser @class {@link User}
     * @return the view login.
     * @throws IllegalArgumentException if the email exists.
     * */
    @PostMapping("/register")
    public String registerUser(@RequestBody User newUser) {
        if (serviceUser.newUser(newUser))
            throw new IllegalArgumentException("The email exists");
        return "login";
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

    private boolean validToken(String token) {
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

}
