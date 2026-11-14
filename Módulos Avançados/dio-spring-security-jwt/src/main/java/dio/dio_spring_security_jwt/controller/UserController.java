package dio.dio_spring_security_jwt.controller;

import dio.dio_spring_security_jwt.model.User;
import dio.dio_spring_security_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postUser(@RequestBody User user){
        service.createUser(user);
    }
}