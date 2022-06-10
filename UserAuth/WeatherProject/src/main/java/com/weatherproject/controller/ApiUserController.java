package com.weatherproject.controller;


import com.weatherproject.domain.ApiUser;
import com.weatherproject.service.ApiUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@Slf4j
public class ApiUserController {
    private final ApiUserService apiUserService;


    @GetMapping(value = "/users")
    public ResponseEntity<List<ApiUser>> getApiUsers(){
        return ResponseEntity.ok().body(apiUserService.getUsers());
    }


    //CREATE
    @PostMapping(value = "/user")
    public ResponseEntity<ApiUser> saveUser(@RequestParam String username, @RequestParam String password){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
        ApiUser apiUser = new ApiUser(null, username, password, "USER_END");

        apiUser = apiUserService.saveUser(apiUser);
        if (apiUser == null)
            return ResponseEntity.unprocessableEntity().body(null);
        log.info("User create : {}", apiUser);
        return ResponseEntity.created(uri).body(apiUser);
    }


    //READ
    @GetMapping(value = "/user/get/")
    public ResponseEntity<ApiUser> getUser(@RequestParam Long id){
        ApiUser apiUser = apiUserService.getUser(id);
        if (apiUser == null)
            return ResponseEntity.notFound().build();
        log.info("get end user : {}", apiUser);
        return ResponseEntity.ok().body(apiUser);
    }


    //UPDATE
    @PostMapping(value = "/user/")
    public ResponseEntity<ApiUser> updateUser(@RequestBody ApiUser apiUser){
        ApiUser returnApiUser = apiUserService.updateUser(apiUser);
        if (returnApiUser==null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(returnApiUser);
    }


    //DELETE
    @DeleteMapping(value = "/user/")
    public ResponseEntity<Boolean> deleteUser(@RequestParam Long id){
        return ResponseEntity.ok().body(apiUserService.deleteUser(id));
    }
}
