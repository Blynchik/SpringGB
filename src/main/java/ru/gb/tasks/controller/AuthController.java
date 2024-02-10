package ru.gb.tasks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/private-data")
    public ResponseEntity<String> getPrivate(){
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/public-data")
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("Success");
    }
}
