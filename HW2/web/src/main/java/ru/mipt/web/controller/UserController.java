package ru.mipt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.web.dto.UserResponseDto;
import ru.mipt.web.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> responseDtos = userService.getAllUsers();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}
