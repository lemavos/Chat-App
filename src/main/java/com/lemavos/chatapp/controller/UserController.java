package com.lemavos.chatapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lemavos.chatapp.service.UserService;
import com.lemavos.chatapp.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<User> searchOneUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOneUserByEmail(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateOneUser(@RequestParam Integer id, 
                                              @RequestBody User user ){
        userService.updateUserByEmail(id, user);
        return ResponseEntity.ok().build();
    }
}
