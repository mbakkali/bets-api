package com.betapi.controllers;

import com.betapi.model.User;
import com.betapi.services.UserRepository;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(DefaultController.class);
    private User notFoundUser = notFoundUser();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @GetMapping()
    @RequestMapping({"/validateLogin/{authorization}"})
    public User validateLogin(@PathVariable String authorization) {//Bad implem
        if(Strings.isNullOrEmpty(authorization)){
            return notFoundUser;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(authorization);
        String username = new String(decodedByteArray).split(":")[0];
        String pwd = new String(decodedByteArray).split(":")[1];
        Optional<User> byId = userRepository.findById(username);

        if(byId.isPresent()){
            if(byId.get().getUserName().equals(username) && byId.get().getPassword().equals(pwd)){
                byId.get().setStatus("OK");
                return byId.get();
            }else {
               return notFoundUser;
            }
        }else {
            return notFoundUser;
        }
    }

    @DeleteMapping(path = {"/users/{id}"})
    public User delete(@PathVariable("id") String id) {
        User deletedEmp = userRepository.findById(id).orElseGet(null);
        if (deletedEmp == null) {
            logger.error("Can't find user with id " + id);
            return null;
        } else {
            userRepository.deleteById(id);
            return deletedEmp;
        }
    }


    @PostMapping(path = "/users")
    public User create(@RequestBody User user) {
        User saved;
        try {
            saved = userRepository.save(user);
            return saved;
        } catch (Exception e) {
            logger.error("Can't save user " + user.toString());
        }
        return null;
    }

    private User notFoundUser(){
        User notFoundUser = new User();
        notFoundUser.setStatus("L'utilisateur n'est pas reconnu");
        return notFoundUser;
    }
}