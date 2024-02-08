package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {

    @Autowired
    private OurUserRepo ourUserRepo;
    @GetMapping("/users")
    public ResponseEntity<Object> getUsers(){
        System.out.println("************User get***************");
        return ResponseEntity.ok(ourUserRepo.findAll());
    }

}
