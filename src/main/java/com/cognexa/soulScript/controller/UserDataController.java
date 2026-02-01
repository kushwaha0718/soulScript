package com.cognexa.soulScript.controller;

import com.cognexa.soulScript.dto.UserDataRequestDTO;
import com.cognexa.soulScript.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserDataController {
    private final UserDataService userDataService;

    @Autowired
    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping
    public ResponseEntity<?> getUserData(@RequestParam String username){
        return new ResponseEntity<>(userDataService.findUserDataByUserName(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveUserData(@RequestBody UserDataRequestDTO userDataRequestDTO) {
        return new ResponseEntity<>(userDataService.saveUserData(userDataRequestDTO), HttpStatus.OK);
    }
}
