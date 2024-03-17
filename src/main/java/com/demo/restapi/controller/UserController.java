package com.demo.restapi.controller;

import com.demo.restapi.dto.User;
import com.demo.restapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public User gteUserById(@PathVariable("id") int id) {

        return service.getUserById(id);
    }

    @GetMapping("/search")
    public List<User> gteUserByName(@RequestParam(value = "name", required = false) String name) {
        return service.getUserByName(name);
    }

}
