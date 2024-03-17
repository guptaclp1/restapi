package com.demo.restapi.service;

import com.demo.restapi.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    List<User> list = Stream.of(new User(1,"Ram",34),
            new User(2,"Shyam",30),
            new User(3,"Aman",32),
    new User(4,"Abhay",31)).collect(Collectors.toList());

    public List<User> getList() {
        return list;
    }

    public User getUserById(int id){
        return getList().stream().filter(user->user.getId()==id).findAny().orElse(null);
    }

    public List<User> getUserByName(String name){
        String username=Optional.ofNullable(name).orElse("");
        if(!username.isEmpty()) {
            return getList().stream().filter(user -> user.getName().toLowerCase().startsWith(name.toLowerCase())).collect(Collectors.toList());
        }else {
            return list;
        }
    }
}
