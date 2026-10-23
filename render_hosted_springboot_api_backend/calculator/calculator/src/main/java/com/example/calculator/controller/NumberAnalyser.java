package com.example.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin; // <-- Import this

@RestController
@CrossOrigin(origins = "*")
public class NumberAnalyser {
    @GetMapping ("/api/analyse-number")
    public Map<String, Object> analyseNumber(@RequestParam int number) {
        String numberType;
        int numberOfDigits;

        if (number < 0) {
            numberType = "Negative";
        } else if (number == 0) {
            numberType = "Zero";
        } else {
            numberType = "Positive";
        }

        // Calculate the number of digits
        numberOfDigits = String.valueOf(Math.abs(number)).length();

        // Return a Map instead of a custom class
        Map<String, Object> response = new HashMap<>();
        response.put("numberType", numberType);
        response.put("numberOfDigits", numberOfDigits);
        //response.put("given number", number); // Include the original number in the response
        response.put("requestedby", "bappa bain"); 
        
        return response;
    }
    
}

// http://localhost:8080/api/analyse-number?number=890450
//https://bain-spring-boot-api.onrender.com/api/analyse-number?number=-230
