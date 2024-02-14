package com.test.dansmultipro.test.dansmultipro.controller;

import com.test.dansmultipro.test.dansmultipro.utils.APIClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    private APIClient apiClient = new APIClient();

    @GetMapping("/recruitment/postions.json")
    public ResponseEntity<?> hitApi(){
        return ResponseEntity.status(HttpStatus.OK).body(apiClient.hitApi());
    }

    @GetMapping("/recruitment/postions/{id}")
    public ResponseEntity<?> details(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(apiClient.findById(id));
    }

}
