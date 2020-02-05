package com.swabber.api.controller;

import com.swabber.api.model.User;
import com.swabber.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// todo configure as bean and separate domain based on profile
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/v1/users")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> allUserList = userService.getAllUserList();
        return new ResponseEntity<>(allUserList, HttpStatus.OK);
    }

//    @GetMapping("/api/v1/users")
//    public ResponseEntity<User> getUser(@RequestParam String name) {
//        final User user = userService.getUser(name);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

}
