package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.dto.response.UserDTO;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        System.out.println("userService: " + userService.hashCode());
        return userService.save(user);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }
    
    @GetMapping("id/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/email/{email}")
    public UserDTO getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

}
