package com.flore.taskagenda.controllers;

import com.flore.taskagenda.models.Activity;
import com.flore.taskagenda.services.ServiceActivity;
import com.flore.taskagenda.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ControllerActivity {

    @Autowired
    private ServiceActivity serviceActivity;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/get/{userid}")
    public List<Activity> getActivities(@RequestHeader(value="Authorization") String token,
                                      @PathVariable int userid) {
        if (!validToken(token))
            throw new IllegalArgumentException("You need login");

        return  serviceActivity.getActivities(userid);
    }

    @PostMapping("/post")
    public void postActivity(@RequestHeader(value="Authorization") String token,
                             @RequestBody Activity activity) {
        if (!validToken(token))
            throw new IllegalArgumentException("You need login");
        serviceActivity.postActivity(activity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActivity(@RequestHeader(value="Authorization") String token,
                              @PathVariable int id) {
        if (!validToken(token))
            throw new IllegalArgumentException("You need login");
        serviceActivity.deleteActivity(id);
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
