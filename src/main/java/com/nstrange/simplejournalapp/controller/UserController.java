package com.nstrange.simplejournalapp.controller;

import com.nstrange.simplejournalapp.entity.JournalEntry;
import com.nstrange.simplejournalapp.entity.User;
import com.nstrange.simplejournalapp.service.JournalEntryService;
import com.nstrange.simplejournalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User userInDb = userService.findByUsername(user.getUsername());
        if(userInDb != null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}